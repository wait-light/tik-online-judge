package top.adxd.tikonlinejudge.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.entity.RoleMenu;
import top.adxd.tikonlinejudge.auth.mapper.RoleMenuMapper;
import top.adxd.tikonlinejudge.auth.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.service.IRoleService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Long> roleMenus(Long roleId) {
        if (roleId == null || roleId == 0) {
            return new ArrayList<>();
        }
        return baseMapper.getMenuIdsByRole(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult setRoleMenus(Long role, Collection<Long> newMenus) {
        if (role == null) {
            return CommonResult.error("角色不存在");
        }
        if (newMenus == null) {
            newMenus = new ArrayList<>();
        }
        List<Long> originalMenus = roleMenus(role);
        Collection<Long> finalNewMenus = newMenus;
        List<Long> needDeleteMenus = originalMenus.stream().filter(item -> !finalNewMenus.contains(item)).collect(Collectors.toList());
        List<RoleMenu> needAddMenus = finalNewMenus.stream()
                .filter(item -> !originalMenus.contains(item))
                .map(item -> {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(item);
                    roleMenu.setRoleId(role);
                    return roleMenu;
                })
                .collect(Collectors.toList());
        if (needDeleteMenus.size() > 0) {
            removeByIds(needDeleteMenus);
        }
        if (needAddMenus.size() > 0) {
            saveBatch(needAddMenus);
        }
        return CommonResult.success("设置成功");
    }
}
