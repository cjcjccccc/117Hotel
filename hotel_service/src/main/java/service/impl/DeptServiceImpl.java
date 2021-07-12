package service.impl;

import dao.DeptMapper;
import entity.Dept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeptService;
import vo.DeptVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-18 17:15
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findDeptList(DeptVo deptVo) {
        return deptMapper.findDeptList(deptVo);
    }

    @Override
    public int insert(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }

    @Override
    public int deleteDeptById(Integer id) {
        return deptMapper.deleteDeptById(id);
    }
}
