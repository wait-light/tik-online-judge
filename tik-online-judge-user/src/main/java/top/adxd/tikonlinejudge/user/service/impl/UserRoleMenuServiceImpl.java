package top.adxd.tikonlinejudge.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import top.adxd.tikonlinejudge.common.util.TransactionUtil;
import top.adxd.tikonlinejudge.user.dto.MenuTree;
import top.adxd.tikonlinejudge.user.dto.RoleWithMenu;
import top.adxd.tikonlinejudge.user.entity.Menu;
import top.adxd.tikonlinejudge.user.entity.Role;
import top.adxd.tikonlinejudge.user.entity.RoleMenu;
import top.adxd.tikonlinejudge.user.mapper.*;
import top.adxd.tikonlinejudge.user.service.IUserRoleMenuService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wait_light
 * @create 2021/9/10
 */

/**
 * 用于处理用户权限的服务
 */
@Service("userRoleMenuService")
public class UserRoleMenuServiceImpl implements IUserRoleMenuService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> userRoles(Long uid) {
        List<Long> roleIdsByUserId = userRoleMapper.getRoleIdsByUserId(uid);
        if (roleIdsByUserId == null || roleIdsByUserId.size() == 0) {
            return new ArrayList<>();
        }
        List<Role> roles = roleMapper.selectBatchIds(roleIdsByUserId);
        return roles;
    }

    @Override
    public List<Menu> roleMenus(Long roleId) {
        List<Long> menuIdsByRole = roleMenuMapper.getMenuIdsByRole(roleId);
        if (menuIdsByRole == null || menuIdsByRole.size() == 0) {
            return new ArrayList<>();
        }
        List<Menu> menus = menuMapper.selectBatchIds(menuIdsByRole);
        return menus;
    }

    @Override
    public List<Menu> userMenus(Long uid) {
        List<Long> roleIdsByUserId = userRoleMapper.getRoleIdsByUserId(uid);
        List<Long> menuIds = new ArrayList<>();
        for (Long roleId : roleIdsByUserId) {
            menuIds.addAll(roleMenuMapper.getMenuIdsByRole(roleId));
        }
        if (menuIds.size() == 0) {
            return new ArrayList<>();
        }
        List<Menu> menus = menuMapper.selectBatchIds(menuIds);
        return menus;
    }

    /**
     * @return 返回组装成树的菜单
     */
    @Override
    public List<MenuTree> menuTree() {
        List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>().orderByDesc("`order`"));
        Map<Long, MenuTree> map = new HashMap<>(menus.size());
        //转换为MenuTree，同时存入map 方便后期快速取出
        List<MenuTree> menuTrees = menus.stream().map((item) -> {
            MenuTree menuTree = new MenuTree();
            menuTree.setChildren(new ArrayList());
            BeanUtils.copyProperties(item, menuTree);
            map.put(menuTree.getId(), menuTree);
            return menuTree;
        }).map((item) -> {
            //子菜单，组装到父菜单中
            if (item.getParentId() != null && item.getParentId() != 0) {
                MenuTree menuTree = map.get(item.getParentId());
                menuTree.getChildren().add(item);
            }
            return item;
            //过滤成只有根菜单的列表，便于转成json
        }).filter(item ->
                item.getParentId() == null || item.getParentId() == 0
        ).collect(Collectors.toList());
        return menuTrees;
    }

    @Transactional
    @Override
    public Boolean SaveRoleWithMenu(@Validated RoleWithMenu roleWithMenu) {
        List<Role> isExistRoleName = roleMapper.selectList(new QueryWrapper<Role>().eq("name", roleWithMenu.getName()).select("id"));
        if (isExistRoleName != null && isExistRoleName.size() >= 1) {
            return false;
        }
        //TODO 添加的用户信息还没弄
        LocalDateTime now = LocalDateTime.now();
        roleWithMenu.setCreateTime(now);
        roleWithMenu.setUpdateTime(now);
        int insert = roleMapper.insert(roleWithMenu);
        //未成功，回滚
        if (insert <= 0) {
            TransactionUtil.rollback();
            return false;
        }
        List<Long> roleMenuIds = roleWithMenu.getRoleMenus();
        for (Long menuId : roleMenuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleWithMenu.getId());
            int success = roleMenuMapper.insert(roleMenu);
            if (success <= 0) {
                TransactionUtil.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean updateRoleWithMenu(@Validated RoleWithMenu roleWithMenu) {
        //TODO 添加的用户信息还没弄
        LocalDateTime now = LocalDateTime.now();
        roleWithMenu.setUpdateTime(now);
        int update = roleMapper.updateById(roleWithMenu);
        //未成功，回滚
        if (update <= 0) {
            TransactionUtil.rollback();
            return false;
        }
        //删除原有权限，再添加新的权限
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", roleWithMenu.getId()));
        List<Long> roleMenuIds = roleWithMenu.getRoleMenus();
        for (Long menuId : roleMenuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleWithMenu.getId());
            int success = roleMenuMapper.insert(roleMenu);
            if (success <= 0) {
                TransactionUtil.rollback();
                return false;
            }
        }
        return true;
    }


}
