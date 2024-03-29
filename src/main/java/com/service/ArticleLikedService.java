package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.ArticleLiked;
import com.vo.params.LikeParams;

/**
* @author 23340
* @description 针对表【blog_article_liked】的数据库操作Service
* @createDate 2022-10-05 12:05:57
*/
public interface ArticleLikedService extends IService<ArticleLiked> {

    ArticleLiked InsertLiked(LikeParams likeParams);
}
