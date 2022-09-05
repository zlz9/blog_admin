package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.Article;
import com.utils.ResponseResult;
import com.vo.params.PageParams;
import com.vo.params.PublishArticleParams;

/**
* @author 23340
* @description 针对表【blog_article】的数据库操作Service
* @createDate 2022-08-17 08:24:01
*/
public interface ArticleService extends IService<Article> {

    ResponseResult getArticle(PageParams pageParams);

    ResponseResult delArticleById(Integer id);

    ResponseResult getArticleByAuthorId();

    ResponseResult publishArticle(PublishArticleParams publishArticleParams);

    ResponseResult articleInfo(Long id);
}
