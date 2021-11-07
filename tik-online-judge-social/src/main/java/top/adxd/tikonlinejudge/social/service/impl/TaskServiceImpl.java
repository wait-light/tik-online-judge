package top.adxd.tikonlinejudge.social.service.impl;

import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.mapper.TaskMapper;
import top.adxd.tikonlinejudge.social.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
