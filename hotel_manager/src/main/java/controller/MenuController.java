package controller;

import entity.Permission;
import entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.PermissionService;
import service.SysUserService;
import utils.MenuNode;
import utils.TreeUtil;
import vo.PermissionVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 有关菜单的控制器
 * @author 陈境聪
 * @date 2021-05-18 11:07
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    @Resource
    private PermissionService permissionService;
    @Resource
    private SysUserService sysUserService;

    /*
     * 加载首页左侧菜单列表
     * @author cjc
     */
    @RequestMapping("/loadLeftMenu")
    @ResponseBody
    public Map loadLeftMenu(PermissionVo permissionVo) {
        //创建Map集合，保存MenuInfo菜单信息
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        //创建Map集合，保存homeInfo信息
        Map<String,Object> homeInfo = new LinkedHashMap<String,Object>();
        //创建Map集合，保存logoInfo信息
        Map<String,Object> logoInfo = new LinkedHashMap<String,Object>();

        //设置只查询菜单
        permissionVo.setType("menu");

//        //获取当前登录用户
//        SysUser loginUser = sysUserService.getUserByUserName(principal.getName());
//
//
//        //调用查询菜单列表的方法
        List<Permission> menuList = permissionService.findPermissionList(permissionVo);
//        List<Permission> menuList = permissionService.findPermissionListByUserId(loginUser.getId(), "menu");

        //创建集合，保存菜单关系
        List<MenuNode> menuNodeList = new ArrayList<MenuNode>();
        //循环遍历菜单集合
        for (Permission permission : menuList) {
            //创建MenuNode对象
            MenuNode menuNode = new MenuNode();
            menuNode.setId(permission.getId());//菜单编号
            menuNode.setPid(permission.getPid());//父级菜单编号
            menuNode.setTitle(permission.getTitle());//标题
            menuNode.setHref(permission.getHref());//跳转地址
            menuNode.setIcon(permission.getIcon());//菜单图标
            menuNode.setSpread(permission.getSpread());//是否展开
            menuNode.setTarget(permission.getTarget());//打开方式
            //将MenuNode对象添加到集合中
            menuNodeList.add(menuNode);
        }
        //保存HomeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/toYearTotalPriceManager.html");//跳转地址
        //保存logoInfo信息
        logoInfo.put("title","117酒店");//logo标题
        logoInfo.put("image","/manager/static/layui/images/logo.png");//logo图片
        logoInfo.put("href","/manager/index.jsp");//首页地址
        //保存菜单menuInfo信息
        map.put("menuInfo", TreeUtil.toTree(menuNodeList,0));
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);
        return map;
    }
}
