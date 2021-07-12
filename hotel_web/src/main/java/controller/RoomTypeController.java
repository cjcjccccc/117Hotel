package controller;

import entity.RoomType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoomTypeService;
import vo.RoomTypeVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-27 19:22
 */
@Controller
@RequestMapping("/roomType")
public class RoomTypeController {
    @Resource
    private RoomTypeService roomTypeService;

    //查找房型列表: 下拉列表专用
    @ResponseBody
    @RequestMapping("/roomTypeList")
    public List roomTypeList(RoomTypeVo roomTypeVo) {
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(roomTypeVo);
        return roomTypeList;
    }

}
