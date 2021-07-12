package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Dept;
import entity.Floor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FloorService;
import utils.DataGridViewResult;
import vo.FloorVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-26 20:23
 */
@Controller
@RequestMapping("/admin/floor")
public class FloorController {
    @Resource
    private FloorService floorService;
    //查询楼层列表
    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(FloorVo floorVo) {
        PageHelper.startPage(floorVo.getPage(),floorVo.getLimit());
        List<Floor> floorList = floorService.findFloorList(floorVo);
        PageInfo<Floor> pageInfo = new PageInfo<>(floorList);
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    //查询楼层列表:下拉列表专用
    @RequestMapping("/floorList")
    @ResponseBody
    public List floorList(FloorVo floorVo) {
        List<Floor> floorList = floorService.findFloorList(floorVo);
        return floorList;
    }
    //添加楼层
    @RequestMapping("/addFloor")
    @ResponseBody
    public Map addFloor(Floor floor) {
        HashMap<String, Object> map = new HashMap<>();
        if (floorService.insert(floor)>0){
            map.put("success",true);
            map.put("message","添加成功");
        }else {
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }
    //修改楼层
    @RequestMapping("/updateFloor")
    @ResponseBody
    public Map updateFloor(Floor floor) {
        HashMap<String, Object> map = new HashMap<>();
        if (floorService.updateFloor(floor)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }
}
