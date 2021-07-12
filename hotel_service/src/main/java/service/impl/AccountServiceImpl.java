package service.impl;

import dao.AccountMapper;
import entity.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.AccountService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-27 17:43
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    /**
     * 登录控制
     * @author cjc
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //创建角色列表集合
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //调用根据用户名查询用户信息的方法
        Account account = accountMapper.getAccountByName(username);
        //创建User对象
        User user = new User(account.getLoginName(),account.getPassword(),
                account.getStatus()==1,
                true,
                true,
                true,
                authorities);
        return user;
    }

    @Override
    public int addAccount(Account account) {
        //为其他属性赋值
        account.setCreateDate(new Date());
        account.setStatus(1);
        System.out.println(account.getPassword());
        //密码加密
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        return accountMapper.addAccount(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountMapper.getAccountByName(name);
    }
}
