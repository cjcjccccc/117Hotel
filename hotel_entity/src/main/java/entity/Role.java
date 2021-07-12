package entity;

/**
 * 角色类
 * @author 陈境聪
 * @date 2021-05-17 20:23
 */
import lombok.Data;

@Data
public class Role {

    private Integer id;//角色编号
    private String roleCode;//角色编码
    private String roleName;//角色名称
    private String roleDesc;//角色描述
}