package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.Article;
import com.utils.ResponseResult;
import com.vo.ArticleInfoVo;
import com.vo.params.LikeParams;
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

    ResponseResult getArticleByAuthorId(PageParams pageParams);

    ResponseResult publishArticle(PublishArticleParams publishArticleParams);

    ResponseResult articleInfo(Long id);

    ResponseResult recommendArticle();

    ResponseResult updateArticle(ArticleInfoVo articleInfoVo);

    ResponseResult Liked(LikeParams likeParams);

    Integer getArticleCountById(Long id);

    ResponseResult searchArticle(String title);

    ResponseResult getArticleView();

    ResponseResult getArticleMonth();

    Integer getArticleCountByUserIdTagId(Long userid, Long tagId);

    ResponseResult getAllArticle();
}

