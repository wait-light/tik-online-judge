package top.adxd.tikonlinejudge.user.service;

import top.adxd.tikonlinejudge.user.dto.MenuTree;
import top.adxd.tikonlinejudge.user.entity.Menu;
import top.adxd.tikonlinejudge.user.entity.Role;
import top.adxd.tikonlinejudge.user.entity.User;

import java.util.List;

/**
 * @author wait_light
 * @create 2021/9/10
 */
public interface IUserRoleMenuService {
    List<Role> userRoles(Long uid);
    List<Menu> roleMenus(Long roleId);
    List<Menu> userMenus(Long uid);
    List<MenuTree> menuTree();
}
