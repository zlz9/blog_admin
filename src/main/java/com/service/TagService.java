package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.Tag;
import com.utils.ResponseResult;
import com.vo.TagVo;
import com.vo.params.TagPageParams;
import com.vo.params.TagParams;

import java.util.List;

/**
* @author 23340
* @description 针对表【blog_tag】的数据库操作Service
* @createDate 2022-08-17 08:24:01
*/
public interface TagService extends IService<Tag> {
     List<TagVo> findTagsByArticleId(Long articleId);
     

    ResponseResult getTagList();

    ResponseResult getArticleByTag(TagPageParams tagPageParams);

    ResponseResult addTag(TagParams tagParams);

    List<TagVo> getTagsById(Long id);

    ResponseResult delTag(Long id);
}
