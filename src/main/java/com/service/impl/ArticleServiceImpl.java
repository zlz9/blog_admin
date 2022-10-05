package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Article;
import com.domain.ArticleTag;
import com.domain.LoginUser;
import com.mapper.ArticleMapper;
import com.mapper.ArticleTagMapper;
import com.service.*;
import com.utils.ResponseResult;
import com.vo.ArticleInfoVo;
import com.vo.ArticleVo;
import com.vo.TagVo;
import com.vo.UserVo;
import com.vo.params.PageParams;
import com.vo.params.PublishArticleParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 23340
 * @description 针对表【blog_article】的数据库操作Service实现
 * @createDate 2022-08-17 08:24:01
 */
@Slf4j
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ThreadService threadService;

    /**
     * 分页查询全部文章
     * @param pageParams
     * @return
     */
    @Override
    public ResponseResult getArticle(PageParams pageParams) {
        /**
         * 分页查询
         */
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateTime);
//        判断是否删除
//        queryWrapper.eq(Article::getDelFlag, "0");
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        long total = articlePage.getTotal();
        List<ArticleVo> articleVoList = copyList(records, true, true);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", articleVoList);
        map.put("total", total);
        log.info("返回的文章信息：{}", map);
        return new ResponseResult(200, map);
    }

    /**
     * 文章删除模块
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult delArticleById(Integer id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        /**
         * 判断是否是文章作者，只有作者才有删除权限
         * 1.获取当前用户信息
         * 2.用户信息比对
         */
        //        获取SecurityContextHolder的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        ;
//        文章作者id
        Long authorId = article.getAuthorId();
        if (userid != authorId) {
            return new ResponseResult(501, "无删除权限");
        }
//        TODO 删除文章的同时删除评论和标签


        /**
         * 逻辑删除
         */
//        article.setDelFlag(1);
//        articleMapper.updateById(article);
        /**
         * 物理删除
         */
        articleMapper.deleteById(id);
        return new ResponseResult(200, "删除成功");
    }

    /**
     * 根据当前作者id查询文章
     *
     * @return
     */
    @Override
    public ResponseResult getArticleByAuthorId(PageParams pageParams) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        查询条件
        queryWrapper.eq(Article::getAuthorId, id);
        queryWrapper.orderByDesc(Article::getCreateTime);
        queryWrapper.like(StringUtils.isNotBlank(pageParams.getQuery()), Article::getTitle, pageParams.getQuery());
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        long total = articlePage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("articleList", records);
        map.put("total", total);
        if (Objects.isNull(articlePage)) {
            return new ResponseResult<>(404, "未找到文章");
        }
        return new ResponseResult(200, map);
    }

    /**
     * 发布文章
     * @param publishArticleParams
     * @return
     */
    @Override
    public ResponseResult publishArticle(PublishArticleParams publishArticleParams) {
        if (ObjectUtils.isEmpty(publishArticleParams)) {
            return new ResponseResult<>(400, "参数错误");
        }

        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        Article article = new Article();
        article.setAuthorId(id);
        article.setCreateTime(System.currentTimeMillis());
        article.setBodyHtml(publishArticleParams.getBodyHtml());
        article.setBodyMd(publishArticleParams.getBodyMd());
        article.setSummary(publishArticleParams.getSummary());
        article.setTitle(publishArticleParams.getTitle());
        article.setWeight(publishArticleParams.getWeight());
        this.articleMapper.insert(article);
        /**
         *文章tags
         * 传过来的是tagId
         */
        List<TagVo> tags = publishArticleParams.getTags();
        ArticleTag articleTag = new ArticleTag();
        if (tags != null) {
            for (TagVo tag : tags) {
                Long articleId = article.getId();
                Long tagId = tag.getTagId();
                articleTag.setTagId(tagId);
                articleTag.setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            }
        }

        HashMap<String, Long> map = new HashMap<>();
        map.put("id", article.getId());
        return new ResponseResult<>(200, "发布成功",map);
    }

    /**
     * 根据文章和id查询文章详情
     * @param id
     * @return
     *
     * public class ArticleInfoVo {
     *     private Long id;
     *     private UserVo userVo;
     *     private String mdBody;
     *     private Long createTime;
     *     private CommentVo commentVo;
     * }
     */
    @Override
    public ResponseResult articleInfo(Long id) {
        ArticleInfoVo articleInfoVo = new ArticleInfoVo();
        Article article = articleMapper.selectById(id);
        if (article == null) {
            return new ResponseResult<>(400,"文章不存在");
        }
        //作者信息
        Long authorId = article.getAuthorId();
        UserVo author = userService.findUserVoById(authorId);
        articleInfoVo.setUserVo(author);
       //文章主体
        articleInfoVo.setId(id);
        articleInfoVo.setHtmlBody(article.getBodyHtml());
        articleInfoVo.setMdBody(article.getBodyMd());
        articleInfoVo.setSummary(article.getSummary());
        articleInfoVo.setTitle(article.getTitle());
      //阅读数加一
        threadService.updateViewCount(articleMapper,article);
       //创建时间
        Long createTime = article.getCreateTime();
        articleInfoVo.setCreateTime(createTime);
        return new ResponseResult<>(200, articleInfoVo);
    }

    /**
     * 文章推送
     * @return
     */
//    TODO 目前先默认推送推送
    @Override
    public ResponseResult recommendArticle() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Article::getCreateTime).last("limit 10");
        List<Article> articleList = articleMapper.selectList(wrapper);
        if (articleList == null) {
            return new ResponseResult(404, "为找到文章");
        }
        return new ResponseResult<>(200,articleList);
    }

    /**
     * 修改文章
     *根据文章id查询文章信息(summary,title,body_html,body_md tags)
     * @param
     * @return
     */
    @Override
    public ResponseResult updateArticle(ArticleInfoVo articleInfoVo) {
        Article article = new Article();
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        article.setId(articleInfoVo.getId());
        article.setTitle(articleInfoVo.getTitle());
        article.setSummary(articleInfoVo.getSummary());
        article.setBodyHtml(articleInfoVo.getHtmlBody());
        article.setBodyMd(articleInfoVo.getMdBody());
        article.setCreateTime(System.currentTimeMillis());
        /**
         * 更新tags
         * 先删除 后插入
         */
        ArticleTag articleTag = new ArticleTag();
        List<TagVo> tags = articleInfoVo.getTags();
        articleTagMapper.deleteById(articleInfoVo.getId());
//        插入
        if (tags != null) {
            for (TagVo tag : tags) {
                articleTag.setArticleId(articleInfoVo.getId());
                articleTag.setTagId(tag.getTagId());
                articleTagMapper.insert(articleTag);
            }
        }
        articleMapper.updateById(article);
        return new ResponseResult<>(200,"成功");
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record, true, true));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            /**
             * 作者信息
             */
            Long authorId = article.getAuthorId();
            articleVo.setNickName(userService.findAuthorById(authorId).getNickName());
            articleVo.setAvator(userService.findAuthorById(authorId).getAvator());
        }
        return articleVo;
    }
}