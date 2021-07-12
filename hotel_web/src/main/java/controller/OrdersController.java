package controller;

import entity.Account;
import entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountService;
import service.OrdersService;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-28 20:37
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;
    @Resource
    private AccountService accountService;

    @RequestMapping("/addOrder")
    @ResponseBody
    public Map addOrders(Orders orders, Principal principal) {
        HashMap<String, Object> map = new HashMap<>();
        //principal 用于获取当前登录用户信息
        String name = principal.getName();
        Account account = accountService.getAccountByName(name);
        //设置订单用户id
        orders.setAccountId(account.getId());
        if (ordersService.addOrders(orders)>0){
            map.put("success",true);
            map.put("message","预约成功");
        }else {
            map.put("success",false);
            map.put("message","添加失败");
        }
        return map;
    }
}
