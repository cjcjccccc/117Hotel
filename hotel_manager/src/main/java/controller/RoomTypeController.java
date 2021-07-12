package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.RoomType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.RoomTypeService;
import utils.DataGridViewResult;
import utils.UUIDUtils;
import vo.DeptVo;
import vo.RoomTypeVo;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-26 21:07
 */
@Controller
@RequestMapping("/admin/roomType")
public class RoomTypeController {
    @Resource
    private RoomTypeService roomTypeService;

    //查询房型列表
    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(RoomTypeVo roomTypeVo) {
        //设置分页信息
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
        //调用方法查询部门列表
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(roomTypeVo);
        //创建分页对象
        PageInfo<RoomType> pageInfo = new PageInfo<>(roomTypeList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    //查找房型列表: 下拉列表专用
    @ResponseBody
    @RequestMapping("/roomTypeList")
    public List roomTypeList(RoomTypeVo roomTypeVo) {
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(roomTypeVo);
        return roomTypeList;
    }
    //添加房型
    @RequestMapping("/addRoomType")
    @ResponseBody
    public Map addRoomType(RoomType roomType) {
        HashMap<String, Object> map = new HashMap<>();
        if (roomTypeService.insert(roomType)>0){
            map.put("success",true);
            map.put("message","添加成功");
        }else {
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }

    //房型图片上传
    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map uploadFile(MultipartFile attach){
        //创建Map集合保存数据(响应到前台页面的数据)
        Map<String,Object> map = new HashMap<String,Object>();
        //判断是否有选中文件
        if(!attach.isEmpty()){
            //获取文件上传地址
            String path = "E:/Java/117upload/upload/room-type-pic/";
            //获取源文件名称
            String oldName = attach.getOriginalFilename();
            //获取文件后缀名
            String extension = FilenameUtils.getExtension(oldName);
            //重命名文件名称
            String newFileName = UUIDUtils.randomUUID()+"."+extension;
            //为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
            String datePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //组装最终的文件名
            String finalName = datePath + "/" + newFileName;
            //创建文件对象
            File destFile = new File(path,finalName);
            //判断文件夹是否存在，文件夹不存在则创建该文件夹
            if(!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }
            try {
                //将文件保存到硬盘
                attach.transferTo(destFile);
                //保存响应的数据
                map.put("code",0);
                map.put("msg","");
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("src","/hotel/show/room-type-pic/"+finalName);//文件上传成功的回显地址
                map.put("data",dataMap);
                map.put("imagePath",finalName);//图片名称，目的是给photo隐藏域赋值
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    //修改房型
    @RequestMapping("/updateRoomType")
    @ResponseBody
    public Map updateRoomType(RoomType roomType) {
        HashMap<String, Object> map = new HashMap<>();
        if (roomTypeService.updateRoomType(roomType)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else {
            map.put("success",false);
            map.put("message","修改失败");
        }
        return map;
    }
    //删除房型
    @RequestMapping("deleteById")
    @ResponseBody
    public Map deleteRoomTypeById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (roomTypeService.deleteRoomTypeById(id)>0){
            map.put("success",true);
            map.put("message","删除成功");
        }else {
            map.put("success",false);
            map.put("message","删除失败");
        }
        return map;
    }
    //检查该房型下有无房间信息
    @ResponseBody
    @RequestMapping("/checkRoomTypeHasRoom")
    public Map checkRoomTypeHasRoom(Integer roomTypeId) {
        HashMap<String, Object> map = new HashMap<>();
        if (roomTypeService.getRoomCountByRoomTypeId(roomTypeId)>0){
            map.put("exist",true);
            map.put("message","该房型存在房间信息,不能删除");
        }else {
            map.put("exist",false);
        }
        return map;
    }

}
