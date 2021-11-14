package top.adxd.tikonlinejudge.auth.service.impl;

import top.adxd.tikonlinejudge.auth.entity.Role;
import top.adxd.tikonlinejudge.auth.mapper.RoleMapper;
import top.adxd.tikonlinejudge.auth.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}