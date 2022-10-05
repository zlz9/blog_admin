package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.LoginUser;
import com.domain.WorkImg;
import com.mapper.WorkImgMapper;
import com.service.WorkImgService;
import com.vo.WorkImgVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23340
* @description 针对表【blog_work_img】的数据库操作Service实现
* @createDate 2022-09-22 16:34:10
*/
@Service
public class WorkImgServiceImpl extends ServiceImpl<WorkImgMapper, WorkImg>
    implements WorkImgService{
    @Autowired
    private WorkImgMapper workImgMapper;

    /**
     * 通过图片id查找图片
     * @param  id
     * @return
     */
    @Override
    public List<WorkImgVo> selectImgByWorkId(Long id) {

        LambdaQueryWrapper<WorkImg> qw = new LambdaQueryWrapper<>();
        qw.eq(WorkImg::getWorkId, id);
        List<WorkImg> workImgs = workImgMapper.selectList(qw);
        List<WorkImgVo> workImgVoList = copyList(workImgs);
        return workImgVoList;
    }

    private List<WorkImgVo> copyList(List<WorkImg> workImgs) {
        ArrayList<WorkImgVo> workImgVos = new ArrayList<>();
        for (WorkImg workImg : workImgs) {
            workImgVos.add(copy(workImg));
        }
        return workImgVos;
    }

    private WorkImgVo copy(WorkImg workImg) {
        WorkImgVo workImgVo = new WorkImgVo();
        BeanUtils.copyProperties(workImg,workImgVo);
        return workImgVo;
    }
}




