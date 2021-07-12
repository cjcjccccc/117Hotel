package controller;

import entity.Checkout;
import entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CheckoutService;
import service.SysUserService;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-29 18:16
 */
@Controller
@RequestMapping("/admin/checkout")
public class CheckoutController {
    @Resource
    private CheckoutService checkoutService;

    @Resource
    private SysUserService sysUserService;
    @ResponseBody
    @RequestMapping("/addCheckout")
    public Map  addCheckout(Checkout checkout, Principal principal) {
        HashMap<String, Object> map = new HashMap<>();
        SysUser user = sysUserService.findUserByUserName(principal.getName());
        //设置创建人
        checkout.setCreatedBy(user.getId());
        //调用方法
        if (checkoutService.addCheckout(checkout)>0){
            map.put("success",true);
            map.put("message","退房成功");
        }else {
            map.put("success",false);
            map.put("message","退房失败");
        }
        return map;
    }
}
