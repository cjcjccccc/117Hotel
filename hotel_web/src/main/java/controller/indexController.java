package controller;

import entity.Floor;
import entity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.FloorService;
import service.RoomService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-27 19:28
 */
@Controller
public class indexController {
    @Resource
    private FloorService floorService;
    @Resource
    private RoomService roomService;

    //进入首页就查询楼层信息,房间信息
    @RequestMapping({"/","/index"})
    public String index(Model model){
        List<Floor> floorList = floorService.findFloorList(null);
        List<Room> roomList = roomService.findRoomList(null);
        model.addAttribute("floorList",floorList);
        model.addAttribute("roomList",roomList);
        return "forward:/index.jsp";
    }
    //进入首页就查询楼层信息,房间信息
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "forward:/login.jsp";
    }
}
