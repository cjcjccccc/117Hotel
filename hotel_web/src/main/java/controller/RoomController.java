package controller;

import entity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.RoomService;
import vo.RoomVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-27 20:13
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    @RequestMapping("/toDetail/{id}.html")
    public String toDetail(@PathVariable Integer id, Model model) {
        Room room = roomService.findRoomById(id);
        model.addAttribute("room",room);
        return "detail";
    }
    @RequestMapping("/list.html")
    public String toRoomList(Model model) {
        RoomVo roomVo = new RoomVo();
        //只查询可预订的房间
        roomVo.setStatus(1);
        List<Room> roomList = roomService.findRoomList(roomVo);
        model.addAttribute("roomList",roomList);
        return "roomList";
    }
    //根据房型查询
    @RequestMapping("/list/{id}.html")
    public String  toRoomList(@PathVariable Integer id,Model model) {
        RoomVo roomVo = new RoomVo();
        //只查询可预订的房间
        roomVo.setStatus(1);
        roomVo.setRoomTypeId(id);
        List<Room> roomList = roomService.findRoomList(roomVo);
        //将房型id添加到model中,目的是在房间页面进行回显
        model.addAttribute("typeId",id);
        model.addAttribute("roomList",roomList);
        return "roomList";
    }
}
