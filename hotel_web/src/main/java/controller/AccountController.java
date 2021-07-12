package controller;

import entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-27 17:49
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/register")
    @ResponseBody
    public Map regist(Account account) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (accountService.addAccount(account)>0){
            map.put("success",true);
            map.put("message","恭喜您，注册成功");
        }else{
            map.put("success",false);
            map.put("message","很遗憾，注册失败");
        }
        return map;
    }
    @RequestMapping("/checkName")
    @ResponseBody
    public Map checkName(String loginName) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (accountService.getAccountByName(loginName)!=null){
            map.put("exist",true);
            map.put("message","用户名已存在!");
        }else{
            map.put("exist",false);
            map.put("message","用户名可以使用");
        }
        return map;
    }
}
