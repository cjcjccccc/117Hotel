package entity;

import lombok.Data;

/**
 * 菜单/权限类
 * @author 陈境聪
 * @date 2021-05-18 10:51
 */
@Data
public class Permission {
    private Integer id;
    private Integer pid;
    private String type;
    private String title;
    private String permissionCode;
    private String icon;
    private String href;
    private Integer spread;//是否展开 1-展开  2-折叠
    private String target;
}
