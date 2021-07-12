package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Dept;
import entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;
import service.SysUserService;
import utils.DataGridViewResult;
import vo.DeptVo;
import vo.RoleVo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有关角色的控制器
 * @author 陈境聪
 * @date 2021-05-18 22:06
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private SysUserService sysUserService;

    //查询角色列表
    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(RoleVo roleVo) {
        //设置分页信息
        PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        //调用方法查询角色列表
        List<Role> deptList = roleService.findRoleList(roleVo);
        //创建分页对象
        PageInfo<Role> pageInfo = new PageInfo<>(deptList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    //添加角色
    @RequestMapping("/addRole")
    @ResponseBody
    public Map addRole(Role role) {
        HashMap<String, Object> map = new HashMap<>();
        if (roleService.insert(role)>0){
            map.put("success",true);
            map.put("message","添加成功");
        }else {
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }
    //修改角色
    @RequestMapping("/updateRole")
    @ResponseBody
    public Map updateRole(Role role) {
        HashMap<String, Object> map = new HashMap<>();
        if (roleService.updateRole(role)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }
    //查询某角色是否有人数
    @RequestMapping("/checkRoleHasUser")
    @ResponseBody
    public Map checkHasCount(Integer roleId) {
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.getRcountByDid(roleId)>0){
            map.put("exist",true);
            map.put("message","角色存在工作人员,不能删除");
        }else {
            map.put("exist",false);
        }
        return map;
    }
    //添加角色
    @RequestMapping("/deleteById")
    @ResponseBody
    public Map deleteRole(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (roleService.deleteRoleById(id)>0){
            map.put("success",true);
            map.put("message","删除成功");
        }else {
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }
    /**
     * 初始化角色列表
     * @param userId
     * @return
     */
    @RequestMapping("/initRoleListByUserId")
    @ResponseBody
    public DataGridViewResult initRoleListByUserId(Integer userId){
        //调用查询所有角色列表的方法
        List<Map<String, Object>> roleListByMap = roleService.findRoleListByMap();
        //调用根据用户ID查询该用户拥有的角色列表方法
        List<Integer> roleListWithUserId = roleService.findRoleListWithUserId(userId);
        //循环遍历两个集合的数据是否出现相同的值(两个集合中的角色ID是否相等，如果相等，表示该用户有这个角色，则需要将复选框选中)
        for (Map<String, Object> map : roleListByMap) {
            //定义变量，标识是否选中
            boolean flag = false;//默认不选中
            //获取角色ID
            Integer roleId = (Integer) map.get("id");//id是角色主键，以主键作为map集合中key
            //内层循环遍历拥有拥有的角色列表
            for (Integer rid : roleListWithUserId) {
                //比较两个集合中的角色ID是否相等
                if(rid == roleId){
                    //修改状态值
                    flag = true;
                    break;
                }
            }
            //将状态保存到Map集合中
            map.put("LAY_CHECKED",flag);//key必须为LAY_CHECKED
        }
        //返回数据
        return new DataGridViewResult(roleListByMap);
    }
}
