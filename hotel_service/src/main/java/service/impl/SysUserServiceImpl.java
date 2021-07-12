package service.impl;

import dao.SysUserMapper;
import entity.Dept;
import entity.Role;
import entity.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SysUserService;
import vo.UserVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-17 11:34
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //创建角色列表集合
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //调用根据用户姓名查询用户信息的方法
        SysUser sysUser = sysUserMapper.findUserByUserName(username);
        //循环遍历用户角色列表
        for (Role role : sysUser.getRoleList()) {
            //将角色编码添加到角色列表集合中
            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        //创建User对象
        User user = new User(sysUser.getUserName(),
                sysUser.getPassword(),
                sysUser.getStatus()==1,
                true,
                true,
                true,
                authorities);
        return user;
    }

    @Override
    public int getDcountByDid(Integer id) {
        return sysUserMapper.getDcountByDid(id);
    }

    @Override
    public int getRcountByDid(Integer id) {
        return sysUserMapper.getRcountByDid(id);
    }

    @Override
    public List<SysUser> findUserList(UserVo userVo) {
        return sysUserMapper.findUserList(userVo);
    }

    @Override
    public int insert(SysUser sysUser) {
        sysUser.setCreateDate(new Date());
        sysUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        sysUser.setModifyDate(new Date());
        return sysUserMapper.updateUser(sysUser);
    }

    @Override
    public int deleteUserById(Integer id) {
        //删除用户角色记录
         sysUserMapper.deleteUserRole(id);
        return sysUserMapper.deleteUserById(id);
    }

    @Override
    public int resetPwd(Integer id) {
        //新建用户
        SysUser sysUser = new SysUser();
        //设置默认密码123456
        sysUser.setId(id);
        sysUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
        sysUser.setModifyDate(new Date());
        return sysUserMapper.updateUser(sysUser);
    }

    @Override
    public boolean saveUserRole(String ids, Integer uid) {
        try {
            //保存用户角色关系前，先将原有的关系清空
            sysUserMapper.deleteUserRole(uid);
            //将ids转为数组
            String[] rids = ids.split(",");
            System.out.println(rids);
            for (int i = 0; i < rids.length; i++) {
                sysUserMapper.saveUserRole(Integer.parseInt(rids[i]), uid);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SysUser findUserByUserName(String userName) {
        return sysUserMapper.findUserByUserName(userName);
    }


}
