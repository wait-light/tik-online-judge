package top.adxd.tikonlinejudge.executor.service.impl;

import top.adxd.tikonlinejudge.executor.entity.Tag;
import top.adxd.tikonlinejudge.executor.mapper.TagMapper;
import top.adxd.tikonlinejudge.executor.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-21
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
