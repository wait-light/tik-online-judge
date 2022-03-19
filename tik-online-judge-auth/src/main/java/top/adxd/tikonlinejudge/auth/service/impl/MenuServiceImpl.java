package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.dto.MenuTree;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.entity.RoleMenu;
import top.adxd.tikonlinejudge.auth.mapper.MenuMapper;
import top.adxd.tikonlinejudge.auth.mapper.RoleMapper;
import top.adxd.tikonlinejudge.auth.mapper.RoleMenuMapper;
import top.adxd.tikonlinejudge.auth.mapper.UserRoleMapper;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.service.IRoleMenuService;
import top.adxd.tikonlinejudge.auth.service.IRoleService;
import top.adxd.tikonlinejudge.auth.service.IUserRoleService;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;

import java.io.Serializable;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @CacheEvict(value = {CacheAblePathMenuMapLoader.PATH_MENU_CACHE_VALUE}, allEntries = true)
    @Override
    public boolean save(Menu entity) {
        return super.save(entity);
    }

    @CacheEvict(value = {PermissionCacheServiceImpl.USER_CACHE_VALUE, CacheAblePathMenuMapLoader.PATH_MENU_CACHE_VALUE}, allEntries = true)
    @Override
    public boolean updateById(Menu entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = {PermissionCacheServiceImpl.USER_CACHE_VALUE, CacheAblePathMenuMapLoader.PATH_MENU_CACHE_VALUE}, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("menu_id", id));
        return super.removeById(id);
    }

    /**
     * @return 返回组装成树的菜单
     */
    @Override
    public List<MenuTree> menuTree() {
        List<Menu> menus = baseMapper.selectList(new QueryWrapper<Menu>().orderByDesc("`order`"));
        return menuTreeBuild(menus);
    }

    private List<MenuTree> menuTreeBuild(List<Menu> menus) {
        if (menus == null || menus.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        Map<Long, MenuTree> map = new HashMap<>(menus.size());
        //转换为MenuTree，同时存入map 方便后期快速取出
        List<MenuTree> menuTrees = menus.stream().map((item) -> {
            MenuTree menuTree = new MenuTree();
            menuTree.setChildren(new ArrayList());
            BeanUtils.copyProperties(item, menuTree);
            map.put(menuTree.getId(), menuTree);
            return menuTree;
        }).collect(Collectors.toList());
        menuTrees.forEach((item) -> {
            //子菜单，组装到父菜单中
            if (item.getParentId() != null && item.getParentId() != 0) {
                MenuTree menuTree = map.get(item.getParentId());
                menuTree.getChildren().add(item);
            }
        });
        return menuTrees     //过滤成只有根菜单的列表，便于转成json
                .stream()
                .filter(item -> item.getParentId() == null || item.getParentId() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuTree> userDirectoryMenuTree(Long uid) {
        if (UserInfoUtil.isAdmin()) {
            return menuTreeBuild(
                    baseMapper.selectList(new QueryWrapper<Menu>()
                            .orderByDesc("`order`")
                            .eq("type", 0)));
        }
        List<Long> roles = roleService.userRoleIdList(uid);
        List<Long> menuIdList = menuIdList(roles);
        return menuTreeBuild(
                baseMapper.selectList(new QueryWrapper<Menu>()
                        .orderByDesc("`order`")
                        .eq("type", 0)
                        .in("id", menuIdList))
        );
    }

    public List<Long> menuIdList(List<Long> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        return roleMenuMapper.selectList(new QueryWrapper<RoleMenu>()
                .in("role_id", roleIds)
                .select("menuId"))
                .stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());
    }
}
