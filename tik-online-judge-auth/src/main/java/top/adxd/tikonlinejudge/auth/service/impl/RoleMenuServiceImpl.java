package top.adxd.tikonlinejudge.auth.service.impl;

import top.adxd.tikonlinejudge.auth.entity.RoleMenu;
import top.adxd.tikonlinejudge.auth.mapper.RoleMenuMapper;
import top.adxd.tikonlinejudge.auth.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
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
