package service;

import entity.Dept;
import entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import vo.UserVo;

import java.util.List;

public interface SysUserService extends UserDetailsService {

    /*
     * 查询某一部门的总人数
     * @author cjc
     */
    int getDcountByDid(Integer dId);
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
    int deleteUserById(Integer id);
    /*
     * 重置密码
     * @author cjc
     */
    int resetPwd(Integer id);

    /*
     * 保存用户角色关系
     * @author cjc
     */
    boolean saveUserRole(String ids, Integer uid);
    /*
     * 根据用户名查询用户信息
     * @author cjc
     */
    SysUser findUserByUserName(String userName);

}
