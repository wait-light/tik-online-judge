package top.adxd.tikonlinejudge.auth.api;

import top.adxd.tikonlinejudge.auth.api.dto.Menu;

import java.util.List;

public interface IRoleService {
    /**
     * 添加角色，并为角色赋予相应权限
     *
     * @param roleName  角色
     * @param menus 菜单
     * @return 是否处理成功
     */
    boolean addRoleWithMenu(String roleName, Menu... menus);

    boolean addRoleWithMenu(String roleName, List<Menu> menus);

    /**
     * 删除角色
     *
     * @param roleName          角色名称
     * @param deleteRelatedMenu 是否连带起权限一并删除
     * @return 是否处理成功
     */
    boolean deleteRole(String roleName, boolean deleteRelatedMenu);

    /**
     * 赋予角色
     *
     * @param uid  用户id
     * @param roleName 角色名称
     * @return 是否处理成功
     */
    boolean grantRole(Long uid, String roleName);

    /**
     * 取消授权角色
     *
     * @param uid  用户id
     * @param roleName 角色名称
     * @return 是否处理成功
     */
    boolean unGrantRole(Long uid, String roleName);

    /**
     * 是否存在某个角色
     * @param roleName 角色名
     * @return 是否存在
     */
    boolean hasRole(String roleName);
}
