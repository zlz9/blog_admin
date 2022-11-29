package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.Tag;
import com.vo.ArticleVo;
import com.vo.TagVo;

import java.util.List;

/**
* @author 23340
* @description 针对表【blog_tag】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.Tag
*/

public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    List<ArticleVo> selectArticleBytagId(Long id);

    List<TagVo> findTagsByUserId(Long id);
}




