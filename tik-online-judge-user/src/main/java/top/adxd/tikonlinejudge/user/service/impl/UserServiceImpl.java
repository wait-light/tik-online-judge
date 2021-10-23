package top.adxd.tikonlinejudge.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import top.adxd.tikonlinejudge.user.api.dto.SafeUserDto;
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

    @Override
    public User getUser(String email) {
        if (email == null || "".equals(email)){
            return null;
        }
        return getOne(new QueryWrapper<User>().eq("email",email));
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null || "".equals(username)){
            return null;
        }
        return getOne(new QueryWrapper<User>().eq("username",username));
    }

    @Override
    public SafeUserDto getSafeUser(Long uid) {
        QueryWrapper<User> select = new QueryWrapper<User>()
                .eq("uid", uid)
                .select("uid", "username", "nickname", "avatar");
        User user = getOne(select);
        SafeUserDto safeUserDto = new SafeUserDto();
        BeanUtils.copyProperties(user, safeUserDto);
        return safeUserDto;
    }
}
