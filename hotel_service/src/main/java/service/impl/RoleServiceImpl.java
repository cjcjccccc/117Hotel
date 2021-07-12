package service.impl;

import dao.RoleMapper;
import dao.SysUserMapper;
import entity.Dept;
import entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RoleService;
import vo.DeptVo;
import vo.RoleVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 陈境聪
 * @date 2021-05-18 22:02
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    /*
     * 查询角色列表
     * @author cjc
     */
    @Override
    public List<Role> findRoleList(RoleVo roleVo) {
        return roleMapper.findRoleList(roleVo);
    }

    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public List<Map<String, Object>> findRoleListByMap() {
        return roleMapper.findRoleListByMap();
    }

    @Override
    public List<Integer> findRoleListWithUserId(Integer userId) {
        return roleMapper.findRoleListWithUserId(userId);
    }


}
