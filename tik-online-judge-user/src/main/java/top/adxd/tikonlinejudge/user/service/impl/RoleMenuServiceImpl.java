package top.adxd.tikonlinejudge.user.service.impl;

import top.adxd.tikonlinejudge.user.entity.RoleMenu;
import top.adxd.tikonlinejudge.user.mapper.RoleMenuMapper;
import top.adxd.tikonlinejudge.user.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Long> userMenus(Long roleId) {
        if (roleId == null || roleId == 0) {
            return new ArrayList<>();
        }
        return baseMapper.getMenuIdsByRole(roleId);
    }
}
