package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.ArticleTag;
import com.service.ArticleTagService;
import com.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_article_tag】的数据库操作Service实现
* @createDate 2022-08-17 08:24:01
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




