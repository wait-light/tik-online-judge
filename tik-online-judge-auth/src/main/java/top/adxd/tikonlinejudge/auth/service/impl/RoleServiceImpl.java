package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.api.dto.Menu;
import top.adxd.tikonlinejudge.auth.dto.RoleDto;
import top.adxd.tikonlinejudge.auth.dto.UserRoleVo;
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
import top.adxd.tikonlinejudge.auth.single.MenuType;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
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
    @Autowired(required = false)
    private top.adxd.tikonlinejudge.auth.entity.Menu systemAutoGenerator;


    @CacheEvict(value = PermissionCacheServiceImpl.USER_CACHE_VALUE,allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        roleMenuService.remove(new QueryWrapper<RoleMenu>()
                .eq("role_id", id));
        return super.removeById(id);
    }

    private static UserRoleVo role2RoleVo(Role item) {
        UserRoleVo roleVo = new UserRoleVo();
        roleVo.setId(item.getId());
        roleVo.setName(item.getName());
        return roleVo;
    }

    @CacheEvict(value = PermissionCacheServiceImpl.USER_CACHE_VALUE,allEntries = true)
    @Override
    public boolean save(Role entity) {
        boolean hasRole = hasRole(entity.getName());
        if (hasRole) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setCreateUserId(UserInfoUtil.getUid());
        entity.setStatus(true);
        return super.save(entity);
    }

    public boolean systemSave(Role entity) {
        boolean hasRole = hasRole(entity.getName());
        if (hasRole) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setCreateUserId(1L);
        entity.setStatus(true);
        return super.save(entity);
    }

    @CacheEvict(value = PermissionCacheServiceImpl.USER_CACHE_VALUE,allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateById(RoleDto roleDto) {
        Role original = getById(roleDto.getId());
        if (original == null) {
            return CommonResult.error("角色不存在");
        }
        if (!original.getName().equals(roleDto.getName())) {
            if (hasRole(roleDto.getName())) {
                return CommonResult.error("角色名已存在");
            }
        }
        LocalDateTime now = LocalDateTime.now();
        original.setName(roleDto.getName());
        original.setUpdateTime(now);
        original.setStatus(roleDto.getStatus() == null ? original.getStatus() : roleDto.getStatus());
        original.setRemark(roleDto.getRemark());
        updateById(original);
        CommonResult setRoleMenusResult = roleMenuService.setRoleMenus(original.getId(), roleDto.getMenus());
        if (!setRoleMenusResult.isSuccess()) {
            return CommonResult.error("更新失败");
        }
        return CommonResult.success("更新成功");
    }

    @Override
    public Role role(String name) {
        return getOne(new QueryWrapper<Role>().eq("name", name));
    }

    @Override
    public Set<String> rolePermissions(String roleName) {
        Role role = role(roleName);
        if (role == null) {
            return new HashSet<>();
        }
        List<Long> menuIds = roleMenuService
                .list(new QueryWrapper<RoleMenu>().eq("role_id", role.getId()).select("menu_id"))
                .stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());

        if (menuIds.size() == 0) {
            return new HashSet<>();
        }
        return menuService.listByIds(menuIds)
                .stream()
                .map(item -> item.getPerms())
                .collect(Collectors.toSet());
    }

    @Override
    public CommonResult userRoles(Long uid) {
        List<Long> roleIds = userRoleIdList(uid);
        if (roleIds.size() == 0) {
            return CommonResult.success().singleData(roleIds);
        }
        List<UserRoleVo> collect = listByIds(roleIds)
                .stream()
                .map(RoleServiceImpl::role2RoleVo)
                .collect(Collectors.toList());
        return CommonResult.success().singleData(collect);
    }

    @Override
    public List<Long> userRoleIdList(Long uid) {
        if (uid == null) {
            return Collections.EMPTY_LIST;
        }
        return userRoleService
                .list(new QueryWrapper<UserRole>().eq("uid", uid).select("role_id"))
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult save(RoleDto roleDto) {
        if (!save((Role) roleDto)) {
            return CommonResult.error("角色名已存在/系统内部出错");
        }
        CommonResult setRoleMenusResult = roleMenuService.setRoleMenus(roleDto.getId(), roleDto.getMenus());
        if (setRoleMenusResult.isSuccess()) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.error("添加失败");
    }


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
        role.setRemark("系统生成");
        role.setUpdateTime(now);
        role.setStatus(true);
        systemSave(role);

        //为自动生成的权限设置一个根菜单，便于分类
        top.adxd.tikonlinejudge.auth.entity.Menu m = new top.adxd.tikonlinejudge.auth.entity.Menu();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(role.getId());
        top.adxd.tikonlinejudge.auth.entity.Menu root = new top.adxd.tikonlinejudge.auth.entity.Menu();
        root.setName(roleName);
        root.setType(MenuType.DIRECTORY);
        root.setPerms(roleName);
        root.setParentId(systemAutoGenerator.getId());
        menuService.save(root);
        roleMenu.setMenuId(root.getId());
        roleMenuService.save(roleMenu);
        //将对应权限添加到角色上
        for (Menu menu : menus) {
            m.setParentId(root.getId());
            m.setType(MenuType.INTERFACE);
            m.setOrder(0);
            BeanUtils.copyProperties(menu, m);
            menuService.save(m);
            roleMenu.setMenuId(m.getId());
            roleMenuService.save(roleMenu);
        }
        return true;
    }

    @Override
    public boolean addRoleWithMenu(String roleName, List<Menu> menus) {
        return addRoleWithMenu(roleName, menus.toArray(new Menu[menus.size()]));
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
        return getOne(new QueryWrapper<Role>().eq("name", roleName)) != null;
    }
}
