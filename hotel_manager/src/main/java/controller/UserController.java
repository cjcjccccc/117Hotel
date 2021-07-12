package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SysUserService;
import utils.DataGridViewResult;
import vo.UserVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有关系统用户的控制器
 * @author 陈境聪
 * @date 2021-05-17 11:41
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    private SysUserService sysUserService;

    /*
     * 退出登录
     * @author cjc
     */
    @RequestMapping("/logout")
    public String loginOut(HttpSession session) {
        session.invalidate();//清空session数据

        return "redirect:/login.jsp";
    }

    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(UserVo userVo) {
        //设置分页信息
        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        //调用方法查询用户列表
        List<SysUser> userList = sysUserService.findUserList(userVo);
        //创建分页对象
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public Map addUser(SysUser sysUser) {
        //创建人
        sysUser.setCreatedBy(1);
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.insert(sysUser)>0) {
            map.put("success",true);
            map.put("message","添加成功");
        }else {
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }
    @RequestMapping("/updateUser")
    @ResponseBody
    public Map updateUser(SysUser sysUser) {
        //修改人
        sysUser.setModifyBy(1);
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.updateUser(sysUser)>0) {
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }
    //删除用户
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Map deleteDeptById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.deleteUserById(id)>0){
            map.put("success",true);
            map.put("message","删除成功");
        }else {
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }
    //重置密码
    @RequestMapping("/resetPwd")
    @ResponseBody
    public Map resetPwd(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.resetPwd(id)>0) {
            map.put("success",true);
            map.put("message","重置成功");
        }else {
            map.put("success",false);
            map.put("message","重置失败");
        }
        return map;
    }
    //分配角色
    @RequestMapping("/grantRole")
    @ResponseBody
    public Map grantRole(String ids,Integer uid) {
        HashMap<String, Object> map = new HashMap<>();
        if (sysUserService.saveUserRole(ids,uid)) {
            map.put("success",true);
            map.put("message","分配成功");
        }else {
            map.put("success",false);
            map.put("message","分配失败");
        }
        return map;
    }
}
