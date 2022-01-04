package top.adxd.tikonlinejudge.auth.dto;

import top.adxd.tikonlinejudge.auth.entity.Role;

import java.util.List;

public class RoleDto extends Role {
    public List<Long> menus;

    public List<Long> getMenus() {
        return menus;
    }
}
