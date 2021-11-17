package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.dto.GroupTaskDto;
import top.adxd.tikonlinejudge.social.entity.GroupTask;
import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.entity.TaskItem;
import top.adxd.tikonlinejudge.social.mapper.GroupTaskMapper;
import top.adxd.tikonlinejudge.social.service.IGroupTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.social.service.ITaskItemService;
import top.adxd.tikonlinejudge.social.service.ITaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class GroupTaskServiceImpl extends ServiceImpl<GroupTaskMapper, GroupTask> implements IGroupTaskService {


}
