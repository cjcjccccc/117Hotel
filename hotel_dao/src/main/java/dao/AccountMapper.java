package dao;

import entity.Account;

public interface AccountMapper {
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
