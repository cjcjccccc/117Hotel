package service;

import entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    /**
     * 添加用户
     * @author cjc
     */
    int addAccount(Account account);
    /**
     * 根据用户名查找用户
     * @author cjc
     */
    Account getAccountByName(String name);
}
