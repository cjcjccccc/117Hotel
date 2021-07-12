package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Checkin;
import entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CheckinService;
import service.SysUserService;
import utils.DataGridViewResult;
import vo.CheckinVo;
import javax.annotation.Resource;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-29 11:21
 */
@Controller
@RequestMapping("/admin/checkin")
public class CheckinController {
    @Resource
    private CheckinService checkinService;
    @Resource
    private SysUserService sysUserService;

    //查询入住订单列表
    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(CheckinVo checkinVo) {
        //设置分页信息
        PageHelper.startPage(checkinVo.getPage(),checkinVo.getLimit());
        //调用方法查询部门列表
        List<Checkin> checkinList = checkinService.findCheckinList(checkinVo);
        //创建分页对象
        PageInfo<Checkin> pageInfo = new PageInfo<>(checkinList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    //登记入住
    @RequestMapping("/addCheckin")
    @ResponseBody
    public Map addCheckin(Checkin checkin, Principal principal) {
        //获取当前登录用户
        SysUser loginUser = sysUserService.findUserByUserName(principal.getName());
        //设置创建人
        checkin.setCreatedBy(loginUser.getId());
        HashMap<String, Object> map = new HashMap<>();
        if (checkinService.addCheckin(checkin)>0){
            map.put("success",true);
            map.put("message","登记成功");
        }else {
            map.put("success",false);
            map.put("message","登记失败");
        }
        return map;
    }
}
