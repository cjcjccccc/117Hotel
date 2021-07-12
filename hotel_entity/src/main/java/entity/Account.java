package entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 前台用户类
 * @author 陈境聪
 * @date 2021-05-27 17:39
 */

@Data
public class Account {
    private Long id;
    private String loginName;
    private String password;
    private String realName;
    private String idCard;
    private String phone;
    private String email;
    private Integer status;
    private Date createDate;

}
