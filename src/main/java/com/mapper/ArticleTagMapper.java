package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.ArticleTag;

/**
* @author 23340
* @description 针对表【blog_article_tag】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.ArticleTag
*/
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    Integer findArticleNumBytag(Long tagId);
}




