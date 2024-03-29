package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.Article;

/**
* @author 23340
* @description 针对表【blog_article】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    Article findArticleInfo(Long id);

    Integer findArticleByUserId(Long id);

    Integer findArticleViewById(Long id);

    Integer findArticleCountByUserIdTagId(Long userid, Long tagId);
}




