package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.auth.api.IUserInfoService;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.social.dto.SolutionDto;
import top.adxd.tikonlinejudge.social.entity.Solution;
import top.adxd.tikonlinejudge.social.mapper.SolutionMapper;
import top.adxd.tikonlinejudge.social.service.ISolutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements ISolutionService {

    private static final String REG_TITLE = "((#+ )\\S+\\s?)|(```\\S*\\s([^`]*)```)|(!?\\[.*\\]\\(.+\\))";
    @DubboReference
    private IUserInfoService userInfoService;

    @Override
    public Long hasSolution(Long uid, Long problemId) {
        Solution one = getOne(new QueryWrapper<Solution>()
                .eq("uid", uid)
                .eq("problem_id", problemId)
                .select("id"));
        if (one == null) {
            return null;
        }
        return one.getId();
    }

    @Override
    public List<Solution> solutionList(Long problemId) {
        PageUtils.makePage();
        List<Solution> collect = baseMapper.selectList(new QueryWrapper<Solution>()
                .eq("problem_id", problemId)
                .eq("status", true)
                .select("title", "content", "id", "create_time", "view"))
                .stream()
                .map(solution -> {
                    solution.setContent(getDisplayContent(solution.getContent()));
                    SolutionDto solutionDto = new SolutionDto();
                    BeanUtils.copyProperties(solution, solutionDto);
                    solutionDto.setUsername(userInfoService.userName(solution.getUid()));
                    return solution;
                })
                .collect(Collectors.toList());
        return collect;
    }

    private String getDisplayContent(String content) {
        //去除markdown格式
        content = content.replaceAll(REG_TITLE, "");
        //截取一部分数据用于展示
        return content.substring(0, Math.min(content.length(), 50));
    }

    @Override
    public List<Solution> userSolutionList(Long uid) {
        PageUtils.makePage();
        return list(new QueryWrapper<Solution>()
                .eq("uid", uid)
                //若非本人查询，则只能查询到已经启用的
                .eq(UserInfoUtil.getUid() != uid, "status", true))
                .stream()
                .map(solution -> {
                    solution.setContent(getDisplayContent(solution.getContent()));
                    SolutionDto solutionDto = new SolutionDto();
                    BeanUtils.copyProperties(solution, solutionDto);
                    solutionDto.setUsername(userInfoService.userName(solution.getUid()));
                    return solutionDto;
                }).collect(Collectors.toList());
    }
}
