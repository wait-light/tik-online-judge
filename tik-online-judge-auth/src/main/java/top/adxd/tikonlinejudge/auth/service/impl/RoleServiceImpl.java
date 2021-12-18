package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.api.dto.Menu;
import top.adxd.tikonlinejudge.auth.entity.Role;
import top.adxd.tikonlinejudge.auth.entity.RoleMenu;
import top.adxd.tikonlinejudge.auth.entity.UserRole;
import top.adxd.tikonlinejudge.auth.mapper.RoleMapper;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
import top.adxd.tikonlinejudge.auth.service.IRoleMenuService;
import top.adxd.tikonlinejudge.auth.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.service.IUserRoleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@DubboService(interfaceClass = top.adxd.tikonlinejudge.auth.api.IRoleService.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService, top.adxd.tikonlinejudge.auth.api.IRoleService {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRoleWithMenu(String roleName, Menu... menus) {
        Role name = getOne(new QueryWrapper<Role>()
                .eq("name", roleName));
        if (name != null) {
            return false;
        }
        if (menus.length == 0) {
            return false;
        }
        Role role = new Role();
        LocalDateTime now = LocalDateTime.now();
        role.setCreateTime(now);
        role.setCreateUserId(-1L);
        role.setName(roleName);
        role.setRemark("其他系统生成");
        role.setUpdateTime(now);
        role.setStatus(true);
        save(role);

        top.adxd.tikonlinejudge.auth.entity.Menu m = new top.adxd.tikonlinejudge.auth.entity.Menu();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(role.getId());
        //将对应权限添加到角色上
        for (Menu menu : menus) {

            m.setParentId(-1L);
            m.setType(2);
            m.setOrder(0);
            BeanUtils.copyProperties(menu, m);
            menuService.save(m);

            roleMenu.setMenuId(m.getId());
            roleMenuService.save(roleMenu);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(String roleName, boolean deleteRelatedMenu) {
        Role role = getOne(new QueryWrapper<Role>()
                .eq("name", roleName));
        if (role == null) {
            return false;
        }
        removeById(role.getId());
        //删除角色对应的权限信息
        if (deleteRelatedMenu) {
            List<Long> menuIds = roleMenuService.list(new QueryWrapper<RoleMenu>()
                            .eq("role_id", role.getId())
                            .select("menu_id"))
                    .stream()
                    .map(RoleMenu::getMenuId)
                    .collect(Collectors.toList());
            roleMenuService.remove(new QueryWrapper<RoleMenu>()
                    .eq("role_id", role.getId()));
            menuService.removeByIds(menuIds);
        }
        return true;
    }

    @Override
    public boolean grantRole(Long uid, String roleName) {
        Role role = getOne(new QueryWrapper<Role>()
                .eq("name", roleName)
                .select("id"));
        if (role == null) {
            return false;
        }
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        userRole.setRoleId(role.getId());
        return userRoleService.save(userRole);
    }

    @Override
    public boolean unGrantRole(Long uid, String roleName) {
        Role role = getOne(new QueryWrapper<Role>()
                .eq("name", roleName)
                .select("id"));
        if (role == null) {
            return false;
        }

        return userRoleService.remove(new QueryWrapper<UserRole>()
                .eq("uid", uid)
                .eq("role_id", role.getId()));
    }

    @Override
    public boolean hasRole(String roleName) {
        return getOne(new QueryWrapper<Role>().eq("name",roleName)) != null;
    }
}
