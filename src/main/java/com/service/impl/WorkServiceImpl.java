 package com.service.impl;

 import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
 import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
 import com.domain.LoginUser;
 import com.domain.Work;
 import com.domain.WorkImg;
 import com.mapper.WorkImgMapper;
 import com.mapper.WorkMapper;
 import com.service.WorkImgService;
 import com.service.WorkService;
 import com.utils.ResponseResult;
 import com.vo.WorkImgVo;
 import com.vo.WorkVo;
 import com.vo.params.WorkParams;
 import org.springframework.beans.BeanUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.ArrayList;
 import java.util.List;

/**
* @author 23340
* @description 针对表【blog_work】的数据库操作Service实现
* @createDate 2022-09-22 16:34:10
*/
@Service
@Transactional
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work>
    implements WorkService{
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private WorkImgService workImgService;
    @Autowired
    private WorkImgMapper workImgMapper;

    /**
     * 获取作品集
     * @return
     */
    @Override
    public ResponseResult getWork() {
       LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        LambdaQueryWrapper<Work> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Work::getAuthorId, id);
        queryWrapper.orderByDesc(Work::getCreateTime);
        List<Work> works = workMapper.selectList(queryWrapper);
        List<WorkVo> workVoList = copyList(works);
        return new ResponseResult(200, workVoList);
    }

    /**
     * 根据作品id获取文章
     * @param id
     * @return
     */
    @Override
    public ResponseResult getWorkInfo(Long id) {
        Work work = workMapper.selectById(id);
        WorkVo workVo = new WorkVo();
        BeanUtils.copyProperties(work, workVo);
        workVo.setCreateTime(work.getCreateTime());
        workVo.setImg(workImgService.selectImgByWorkId(id));
        return new ResponseResult(200, workVo);
    }

    /**
     * 上传作品模块
     * @param workParams
     * @return
     */
    @Override
    public ResponseResult updateWork(WorkParams workParams) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        Work work = new Work();
        work.setCreateTime(System.currentTimeMillis());
        work.setAuthorId(id);
        work.setDescription(workParams.getDescription());
        work.setLinkGitee(workParams.getLinkGitee());
        work.setLinkGithub(workParams.getLinkGithub());
        work.setName(workParams.getName());
        work.setPreview(workParams.getPreview());
        work.setDescription(workParams.getDescription());
//        根据作品id插入图片
        workMapper.insert(work);
        List<WorkImgVo> imgUrls = workParams.getUrls();
        WorkImg workImg = new WorkImg();
        for (WorkImgVo imgUrl : imgUrls) {
            String url = imgUrl.getUrl();
            workImg.setWorkId(work.getId());
            workImg.setUrl(url);
            workImgMapper.insert(workImg);
        }
        return new ResponseResult<>(200,"上传成功");
    }

    /**
     * 根据id删除作品
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteWork(Long id) {
        workMapper.deleteById(id);
        LambdaQueryWrapper<WorkImg> wq = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<WorkImg> eq = wq.eq(WorkImg::getWorkId, id);
        workImgMapper.delete(eq);
        return new ResponseResult(200, "删除成功");
    }

    private List<WorkVo> copyList(List<Work> works) {
        ArrayList<WorkVo> workVoList = new ArrayList<>();
        for (Work work : works) {
            workVoList.add(copy(work));
        }
        return workVoList;
    }

    private WorkVo copy(Work work) {
        WorkVo workVo = new WorkVo();
        BeanUtils.copyProperties(work, workVo);
//        根据作品id查找作品图片
        workVo.setImg(workImgService.selectImgByWorkId(workVo.getId()));
        return workVo;
    }
}