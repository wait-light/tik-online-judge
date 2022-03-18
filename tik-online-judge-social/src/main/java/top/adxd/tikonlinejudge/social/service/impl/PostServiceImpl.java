package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.IUserInfoService;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.dto.SolutionDto;
import top.adxd.tikonlinejudge.social.entity.Solution;
import top.adxd.tikonlinejudge.social.service.IPostService;
import top.adxd.tikonlinejudge.social.service.ISolutionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private ISolutionService solutionService;
    @DubboReference
    private IUserInfoService userInfoService;
    //    private static final String SPECIAL_SYMBOLS = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%…‘；：”“’。， 、]";
//    private static final String REG = "(#+ \\S+\\s)|(\\.+ \\S+\\s)|(```\\S+\\s)";
    private static final String REG_TITLE = "((#+ )\\S+\\s?)|(```\\S*\\s([^`]*)```)|(!?\\[.*\\]\\(.+\\))";

    @Override
    public CommonResult post(Solution post) {
        LocalDateTime now = LocalDateTime.now();
        Long uid = UserInfoUtil.getUid();
        post.setCreateTime(now);
        post.setUpdateTime(now);
        post.setUid(uid);
        post.setProblemId(null);
        return solutionService.save(post) ?
                CommonResult.success("发表成功").add("id", post.getId()) :
                CommonResult.error("发表失败");
    }

    @Override
    public CommonResult updatePost(Solution post) {
        LocalDateTime now = LocalDateTime.now();
        Solution p = solutionService.getById(post.getId());
        if (p == null) {
            return post(post);
        }
        Long uid = UserInfoUtil.getUid();
        if (uid != p.getUid()) {
            return CommonResult.permissionDeny("禁止访问");
        }
        p.setUpdateTime(now);
        p.setContent(post.getContent());
        p.setStatus(post.getStatus());
        return solutionService.updateById(p) ?
                CommonResult.success("更新成功") :
                CommonResult.error("更新失败");
    }

    @Override
    public CommonResult deletePost(Long postId) {
        Solution one = solutionService.getOne(new QueryWrapper<Solution>()
                .eq("id", postId)
                .select("uid"));
        if (one == null || one.getUid() != UserInfoUtil.getUid()) {
            return CommonResult.permissionDeny("禁止访问");
        }
        return solutionService.removeById(postId) ?
                CommonResult.success("删除成功") :
                CommonResult.error("删除失败");
    }

    @Override
    public CommonResult postList(Integer type) {
        List<Solution> posts = null;
        if (type == 1 || type == 2) {
            PageUtils.makePage();
            posts = solutionService.list(new QueryWrapper<Solution>()
                    .eq("status", true)
                    .isNull("problem_id"));
        } else {
            posts = random();
        }
        posts = posts.stream().map(post -> {
            post.setContent(getDisplayContent(post.getContent()));
            SolutionDto solutionDto = new SolutionDto();
            BeanUtils.copyProperties(post, solutionDto);
            solutionDto.setUsername(userInfoService.userName(post.getUid()));
            return solutionDto;
        }).collect(Collectors.toList());
        return CommonResult.success().listData(posts);
    }

    private String getDisplayContent(String content) {
        //去除markdown格式
        content = content.replaceAll(REG_TITLE, "");
        //截取一部分数据用于展示
        return content.substring(0, Math.min(content.length(), 50));
    }


    @Override
    public CommonResult getPost(Long postId) {
        Solution post = solutionService.getById(postId);
        if (post == null) {
            return CommonResult.permissionDeny("禁止访问");
        }
        Long uid = UserInfoUtil.getUid();
        if (post.getUid() != uid) {
            return CommonResult.permissionDeny("禁止访问");
        }
        return CommonResult.success().singleData(post);
    }

    private List<Solution> random() {
        return solutionService.list(new QueryWrapper<Solution>()
                .eq("status", true)
                .isNull("problem_id")
                .last("ORDER BY RAND() LIMIT 10"));
    }

}
