package com.mapper;

import com.domain.ArticleLiked;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_article_liked】的数据库操作Mapper
* @createDate 2022-10-05 12:05:57
* @Entity com.domain.ArticleLiked
*/
@Mapper
public interface ArticleLikedMapper extends BaseMapper<ArticleLiked> {
    Integer getTotal(Long articleId);
}




