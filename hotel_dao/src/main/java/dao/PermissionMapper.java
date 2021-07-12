package dao;

import entity.Permission;
import vo.PermissionVo;

import java.util.List;

public interface PermissionMapper {
    /*
     * 查询菜单列表
     * @author cjc
     */
    List<Permission> findPermissionList(PermissionVo permissionVo);
}
