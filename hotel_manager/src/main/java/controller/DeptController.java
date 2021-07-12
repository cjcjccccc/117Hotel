package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DeptService;
import service.SysUserService;
import utils.DataGridViewResult;
import vo.DeptVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有关部门管理的控制器
 * @author 陈境聪
 * @date 2021-05-18 17:17
 */
@Controller
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;
    @Resource
    private SysUserService sysUserService;

    //查询部门列表
    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(DeptVo deptVo) {
        //设置分页信息
        PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());
        //调用方法查询部门列表
        List<Dept> deptList = deptService.findDeptList(deptVo);
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<>(deptList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    //获取部门列表list
    @RequestMapping("/findDeptList")
    @ResponseBody
    public List deptList(DeptVo deptVo) {
        List<Dept> deptList = deptService.findDeptList(deptVo);
        return deptList;
    }

    //添加部门
    @RequestMapping("/addDept")
    @ResponseBody
    public Map addDept(Dept dept) {
        dept.setCreateTime(new Date());
        HashMap<String, Object> map = new HashMap<>();
        if (deptService.insert(dept)>0){
            map.put("success",true);
            map.put("message","添加成功");
        }else {
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }
    //修改部门
    @RequestMapping("/updateDept")
    @ResponseBody
    public Map updateDept(Dept dept) {
        HashMap<String, Object> map = new HashMap<>();
        if (deptService.updateDept(dept)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }
    //查询某部门是否有人数
    @RequestMapping("/checkDeptHasUser")
    @ResponseBody
    public Map checkHasCount(Integer deptId) {
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.getDcountByDid(deptId)>0){
            map.put("exist",true);
            map.put("message","部门存在工作人员,不能删除");
        }else {
            map.put("exist",false);
        }
        return map;
    }
    //删除部门
    @RequestMapping("/deleteDeptById")
    @ResponseBody
    public Map deleteDeptById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (deptService.deleteDeptById(id)>0){
            map.put("success",true);
            map.put("message","删除成功");
        }else {
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }
}
