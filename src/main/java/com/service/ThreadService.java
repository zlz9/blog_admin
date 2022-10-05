package com.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.domain.Article;
import com.mapper.ArticleMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <h4>blog_admin</h4>
 * <p>线程池</p>
 *
 * @author : zlz
 * @date : 2022-10-02 08:38
 **/
@Component
public class ThreadService {
    @Async("taskExecutor")
    public void updateViewCount(ArticleMapper articleMapper, Article article) {
        Integer viewCount = article.getViewCount();
        Article articleUpdate = new Article();
        articleUpdate.setViewCount(viewCount+1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, article.getId());
        queryWrapper.eq(Article::getViewCount, viewCount);
        articleMapper.update(articleUpdate, queryWrapper);
    }
}
