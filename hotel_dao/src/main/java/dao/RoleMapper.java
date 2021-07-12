package dao;

import entity.Role;
import entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vo.RoleVo;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    /*
     * 根据用户id查询角色列表
     * @author cjc
     */
    List<Role> findRoleListByUserId(Integer id);
    /*
     * 查询角色列表
     * @author cjc
     */
    List<Role> findRoleList(RoleVo roleVo);
    /*
     * 添加角色
     * @author cjc
     */
    int insert(Role role);
    /*
     * 修改角色
     * @author cjc
     */
    int updateRole(Role role);
    /*
     * 删除角色
     * @author cjc
     */
    int deleteRoleById(@Param("id") Integer id);
    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from sys_role")
    List<Map<String,Object>> findRoleListByMap();


    /**
     * 根据用户ID查询该用户拥有的角色列表(只查询角色ID)
     * @param userId
     * @return
     */
    @Select("select rid from sys_role_user where uid = #{userId}")
    List<Integer> findRoleListWithUserId(Integer userId);

}
