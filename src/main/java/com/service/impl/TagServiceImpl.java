package com.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Tag;
import com.mapper.TagMapper;
import com.service.TagService;
import com.utils.ResponseResult;
import com.vo.ArticleVo;
import com.vo.TagVo;
import com.vo.params.TagPageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 23340
 * @description 针对表【blog_tag】的数据库操作Service实现
 * @createDate 2022-08-17 08:24:01
 */
@Service
@Transactional
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        /**
         * 返回vo层
         */
        return copyList(tags);
    }

    @Override
    public ResponseResult addTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagMapper.insert(tag);

        return new ResponseResult<>(200, "添加标签成功");
    }

    /**
     * 获取标签列表
     * @return
     */
    @Override
    public ResponseResult getTagList() {

        List<Tag> tags = tagMapper.selectList(null);
        if (tags == null) {
            return new ResponseResult<>(400,"标签不存在");
        }
        return new ResponseResult(200, tags);
    }

    /**
     * 根据tagId查找文章
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleByTag(TagPageParams tagPageParams) {
        List<ArticleVo> articleList =tagMapper.selectArticleBytagId(tagPageParams.getId());
        Page<ArticleVo> articlePage = new Page<>(tagPageParams.getPage(),tagPageParams.getPageSize());
        List<ArticleVo> records = articlePage.getRecords();
        long total = articlePage.getTotal();
        if (articleList == null) {
            return new ResponseResult<>(400,"文章不存在");
        }
        return new ResponseResult<>(200, articleList);
    }

    /**
     * 将返回tagVo
     * @param tagList
     * @return
     */
    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    /**
     * 复制tagVo
     * @param tag
     * @return
     */
    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }
}




