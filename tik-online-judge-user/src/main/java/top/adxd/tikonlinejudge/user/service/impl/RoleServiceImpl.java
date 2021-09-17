package top.adxd.tikonlinejudge.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.util.TransactionUtil;
import top.adxd.tikonlinejudge.user.entity.Role;
import top.adxd.tikonlinejudge.user.entity.RoleMenu;
import top.adxd.tikonlinejudge.user.mapper.RoleMapper;
import top.adxd.tikonlinejudge.user.mapper.RoleMenuMapper;
import top.adxd.tikonlinejudge.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 同时删除角色，以及其对应的关系
     *
     * @param roleId 被删除的角色的id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteRoleWithMenu(Long roleId) {
        //非法id
        if (roleId == null || roleId == 0) {
            return false;
        }
        int deleteSuccess = baseMapper.deleteById(roleId);
        if (deleteSuccess <= 0) {
            TransactionUtil.rollback();
            return false;
        }
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        return true;
    }

    @Override
    public boolean deleteRoleBatchWithMenu(Long[] roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return false;
        }
        int deleteBatchSuccess = baseMapper.deleteBatchIds(Arrays.asList(roleIds));
        if (deleteBatchSuccess < roleIds.length) {
            TransactionUtil.rollback();
            return false;
        }
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().in("role_id", roleIds));
        return true;
    }
}
