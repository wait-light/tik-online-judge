package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.auth.dto.MenuTree;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.mapper.MenuMapper;
import top.adxd.tikonlinejudge.auth.mapper.RoleMapper;
import top.adxd.tikonlinejudge.auth.mapper.RoleMenuMapper;
import top.adxd.tikonlinejudge.auth.mapper.UserRoleMapper;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public boolean removeById(Serializable id) {

        return super.removeById(id);
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
}
