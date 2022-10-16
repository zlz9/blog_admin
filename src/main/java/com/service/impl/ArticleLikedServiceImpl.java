package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.ArticleLiked;
import com.service.ArticleLikedService;
import com.mapper.ArticleLikedMapper;
import com.utils.RedisCache;
import com.vo.params.LikeParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_article_liked】的数据库操作Service实现
* @createDate 2022-10-05 12:05:57
*/
@Service
public class ArticleLikedServiceImpl extends ServiceImpl<ArticleLikedMapper, ArticleLiked>
    implements ArticleLikedService{
    @Autowired
    private ArticleLikedMapper articleLikedMapper;
    @Autowired
    private RedisCache redisCache;
    /**
     * 点赞
     * @param likeParams
     * @return
     */
    @Override
    public ArticleLiked InsertLiked(LikeParams likeParams) {
        ArticleLiked articleLiked = new ArticleLiked();
        articleLiked.setArticleId(likeParams.getArticleId());
        articleLiked.setUserId(likeParams.getUserId());
        articleLiked.setLikeStatus(likeParams.isStatus());
        articleLikedMapper.insert(articleLiked);
        return articleLiked;
    }

}




