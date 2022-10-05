package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Interview;
import com.domain.LoginUser;
import com.service.InterviewService;
import com.mapper.InterviewMapper;
import com.utils.ResponseResult;
import com.vo.params.InterViewParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
* @author 23340
* @description 针对表【blog_interview】的数据库操作Service实现
* @createDate 2022-09-29 11:15:33
*/
@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview>
    implements InterviewService{
  @Autowired
  private InterviewMapper interviewMapper;

    /**
     * 获取面试模块的列表
     * @return
     */
    @Override
    public ResponseResult getInterView() {
        LambdaQueryWrapper<Interview> wq = new LambdaQueryWrapper<>();
        wq.orderByDesc(Interview::getCreateTime);
        List<Interview> interviews = interviewMapper.selectList(wq);
        if (ObjectUtils.isEmpty(interviews)) {
            return new ResponseResult<>(404,"暂无");
        }
        return new ResponseResult<>(200, interviews);
    }

    /**
     * 上传面试
     * @param interViewParams
     * @return
     */
    @Override
    public ResponseResult uploadInterView(InterViewParams interViewParams) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        Interview interview = new Interview();
        interview.setAuthorId(id);
        interview.setCover(interViewParams.getCover());
        interview.setCreateTime(System.currentTimeMillis());
        interview.setLink(interViewParams.getLink());
        interview.setName(interViewParams.getName());
        interview.setSummary(interViewParams.getSummary());
        int insert = interviewMapper.insert(interview);
        return new ResponseResult<>(200,"插入成功");
    }
}




