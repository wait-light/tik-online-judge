package top.adxd.tikonlinejudge.auth.service.impl;

import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.mapper.MenuMapper;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
