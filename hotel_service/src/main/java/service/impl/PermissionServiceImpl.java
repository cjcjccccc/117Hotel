package service.impl;

import dao.PermissionMapper;
import entity.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.PermissionService;
import vo.PermissionVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-18 11:05
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionList(PermissionVo permissionVo) {
        return permissionMapper.findPermissionList(permissionVo);
    }
}
