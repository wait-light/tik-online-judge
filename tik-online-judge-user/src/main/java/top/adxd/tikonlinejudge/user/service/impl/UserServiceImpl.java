package top.adxd.tikonlinejudge.user.service.impl;

import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.mapper.UserMapper;
import top.adxd.tikonlinejudge.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
