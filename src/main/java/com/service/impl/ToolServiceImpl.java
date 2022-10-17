package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.LoginUser;
import com.domain.Tool;
import com.service.ToolService;
import com.mapper.ToolMapper;
import com.utils.ResponseResult;
import com.vo.params.RootPage;
import com.vo.params.ToolParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;

/**
* @author 23340
* @description 针对表【blog_tool】的数据库操作Service实现
* @createDate 2022-09-29 17:08:23
*/
@Service
@Transactional
public class ToolServiceImpl extends ServiceImpl<ToolMapper, Tool>
    implements ToolService{
    @Autowired
    private ToolMapper toolMapper;
    /**
     * 获取当前用户的开发工具信息
     * @return
     */
    @Override
    public ResponseResult getTool(RootPage rootPage) {
        Page<Tool> page = new Page<>(rootPage.getPage(),rootPage.getPageSize());
        LoginUser  loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LambdaQueryWrapper<Tool> wq = new LambdaQueryWrapper<>();
        wq.orderByDesc(Tool::getCreateTime);
        wq.eq(Tool::getAuthorId, loginUser.getUser().getId());
        Page<Tool> toolPage = toolMapper.selectPage(page, wq);
        List<Tool> records = toolPage.getRecords();
        long total = toolPage.getTotal();
        if (ObjectUtils.isEmpty(records)) {
            return new ResponseResult(404, "找不到工具");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data", records);
        return new ResponseResult<>(200,map);
    }

    /**
     * 发布工具
     * @param toolParams
     * @return
     */
    @Override
    public ResponseResult uploadTool(ToolParams toolParams) {
       LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tool tool = new Tool();
        tool.setAuthorId(loginUser.getUser().getId());
        tool.setCover(toolParams.getCover());
        tool.setLink(toolParams.getLink());
        tool.setName(toolParams.getName());
        tool.setSummary(toolParams.getSummary());
        tool.setCreateTime(System.currentTimeMillis());
        int insert = toolMapper.insert(tool);
        if (insert == 0) {
            return new ResponseResult<>(400,"发布失败");
        }
        return new ResponseResult<>(200,"发布成功");
    }

    /**
     * 根据id删除工具
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteToolById(Long id) {
        int i = toolMapper.deleteById(id);
        if (i == 0) {
            return new ResponseResult<>(400,"删除失败");
        }
        return new ResponseResult<>(200,"删除成功");
    }

    /**
     * 根据用户id查询用户上传的工具数
     * @param id
     * @return
     */
    @Override
    public Integer getToolCountById(Long id) {
     Integer toolCount = toolMapper.findToolCountById(id);
        return toolCount;
    }
}

