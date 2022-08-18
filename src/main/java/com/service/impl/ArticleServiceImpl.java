package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Article;
import com.domain.LoginUser;
import com.mapper.ArticleMapper;
import com.service.ArticleService;
import com.service.TagService;
import com.service.UserService;
import com.utils.ResponseResult;
import com.vo.ArticleVo;
import com.vo.params.PageParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 23340
 * @description 针对表【blog_article】的数据库操作Service实现
 * @createDate 2022-08-17 08:24:01
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult getArticle(PageParams pageParams) {
        /**
         * 分页查询
         */
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateTime);
//        判断是否删除
        queryWrapper.eq(Article::getDelFlag, "0");
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        long total = articlePage.getTotal();
        List<ArticleVo> articleVoList = copyList(records, true, true);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",articleVoList);
        map.put("total",total);
        log.info("返回的文章信息：{}",map);
        return new ResponseResult(200, map);
    }

    /**
     * 文章删除模块
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
        if (userid!=authorId){
           return new ResponseResult(501, "无删除权限");
        }
        article.setDelFlag(1);
        articleMapper.updateById(article);
        return new ResponseResult(200, "删除成功");
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
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(userService.findAuthorById(authorId).getUserName());
        }
        return articleVo;
    }
}