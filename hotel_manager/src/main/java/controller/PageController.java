package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 专门用作跳转页面的控制器
 * @author 陈境聪
 * @date 2021-05-18 16:35
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    /*
     * 跳转到部门管理页面
     * @author cjc
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager() {
        return "/dept/deptManager";
    }
    /*
     * 跳转到角色管理页面
     * @author cjc
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "/role/roleManager";
    }
    /*
     * 跳转到用户管理页面
     * @author cjc
     */
    @RequestMapping("/toUserManager")
    public String toUserManager() {
        return "/user/userManager";
    }
    /*
     * 跳转到楼层管理页面
     * @author cjc
     */
    @RequestMapping("/toFloorManager")
    public String toFloorManager() {
        return "/floor/floorManager";
    }
    /*
     * 跳转到房型管理页面
     * @author cjc
     */
    @RequestMapping("/toRoomTypeManager")
    public String toRoomTypeManager() {
        return "/roomType/roomTypeManager";
    }
    /*
     * 跳转到房间管理页面
     * @author cjc
     */
    @RequestMapping("/toRoomManager")
    public String toRoomManager() {
        return "/room/roomManager";
    }
    /*
     * 跳转到房间管理页面
     * @author cjc
     */
    @RequestMapping("/toOrdersManager")
    public String toOrdersManager() {
        return "/orders/ordersManager";
    }
    /*
     * 跳转到入住管理页面
     * @author cjc
     */
    @RequestMapping("/toCheckInManager")
    public String toCheckInManager() {
        return "/checkin/checkinManager";
    }
}

