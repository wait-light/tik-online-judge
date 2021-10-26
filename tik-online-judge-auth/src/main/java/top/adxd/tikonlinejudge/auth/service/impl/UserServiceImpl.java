package top.adxd.tikonlinejudge.auth.service.impl;

import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.auth.mapper.UserMapper;
import top.adxd.tikonlinejudge.auth.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
