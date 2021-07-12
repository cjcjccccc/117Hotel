package dao;

import entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vo.UserVo;

import java.util.List;
public interface SysUserMapper {
   /*
    * 根据用户名查询用户信息
    * @author cjc
    */
    SysUser findUserByUserName(String userName);
    /*
     * 查询某一部门的总人数
     * @author cjc
     */
    int getDcountByDid(Integer id);
    /*
     * 查询某一角色的总人数
     * @author cjc
     */
    int getRcountByDid(Integer id);

    /*
     * 查询用户列表
     * @author cjc
     */
    List<SysUser> findUserList(UserVo userVo);
    /*
     * 添加用户
     * @author cjc
     */
    int insert(SysUser sysUser);
    /*
     * 修改用户
     * @author cjc
     */
    int updateUser(SysUser sysUser);
    /*
     * 删除用户
     * @author cjc
     */
    int deleteUserById(@Param("id") Integer id);
    /*
     * 删除用户角色记录
     * @author cjc
     */
    int deleteUserRole(@Param("id") Integer id);
    /*
    保存用户角色关系
     */
    @Insert("insert into sys_role_user(uid,rid) values (#{uid},#{rid})")
    void saveUserRole(@Param("rid") int rid,@Param("uid") Integer uid);

}
