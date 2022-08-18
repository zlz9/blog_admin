package com.mapper;

import com.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_article】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}



