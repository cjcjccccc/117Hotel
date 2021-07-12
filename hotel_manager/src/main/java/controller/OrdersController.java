package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Dept;
import entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrdersService;
import utils.DataGridViewResult;
import vo.OrdersVo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-28 22:30
 */
@Controller
@RequestMapping("/admin/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    //查询订单列表
    @RequestMapping("/list")
    @ResponseBody
    public DataGridViewResult list(OrdersVo ordersVo) {
        //设置分页信息
        PageHelper.startPage(ordersVo.getPage(),ordersVo.getLimit());
        //调用方法查询部门列表
        List<Orders> ordersList = ordersService.findOrdersList(ordersVo);
        //创建分页对象
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    //修改订单
    @RequestMapping("/confirmOrders")
    @ResponseBody
    public Map ConfirmOrders(Orders orders) {
        //修改订单状态
        orders.setStatus(2);
        HashMap<String, Object> map = new HashMap<>();
        if (ordersService.update(orders)>0){
            map.put("success",true);
            map.put("message","订单确认成功");
        }else {
            map.put("success",false);
            map.put("message","订单确认失败");
        }
        return map;
    }
    //批量确认订单
    @RequestMapping("/batchConfirm")
    @ResponseBody
    public Map BatchConfirm(String ids) {

        HashMap<String, Object> map = new HashMap<>();
        int count=0;

        String[] id = ids.split(",");
        for (int i = 0; i <id.length ; i++) {
            Orders orders = new Orders();
            orders.setId(Long.valueOf(id[i]));
            //修改订单状态
            orders.setStatus(2);
             count = ordersService.update(orders);
            if (count>0){
                map.put("success",true);
                map.put("message","订单确认成功");
            }
        }
        if (count<=0){
            map.put("success",false);
            map.put("message","订单确认失败");
        }
        return map;
    }
}
