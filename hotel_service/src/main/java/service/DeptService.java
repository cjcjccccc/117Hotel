package service;

import entity.Dept;
import org.apache.ibatis.annotations.Delete;
import vo.DeptVo;

import java.util.List;

public interface DeptService {
    /*
     * 查询部门列表
     * @author cjc
     */
    List<Dept> findDeptList(DeptVo deptVo);
    /*
     * 添加部门
     * @author cjc
     */
    int insert(Dept dept);
    /*
     * 修改部门
     * @author cjc
     */
    int updateDept(Dept dept);
    /*
     * 删除部门
     * @author cjc
     */
    int deleteDeptById(Integer id);
}
