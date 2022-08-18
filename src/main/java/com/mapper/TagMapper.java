package com.mapper;

import com.domain.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 23340
* @description 针对表【blog_tag】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);
}



