package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.social.entity.Solution;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
public interface ISolutionService extends IService<Solution> {
    /**
     * 查询某个用户是否已经为某个问题编写题解了
     * @param uid 用户id
     * @param problemId 问题id
     * @return 题解ID，若是没有对应题解，返回NULL
     */
    Long hasSolution(Long uid,Long problemId);
    List<Solution> solutionList(Long problemId);
}
