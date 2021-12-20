package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Comment;
import top.adxd.tikonlinejudge.social.service.ICommentService;
import top.adxd.tikonlinejudge.social.service.ISolutionCommentSolutionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionCommentSolutionServiceImpl implements ISolutionCommentSolutionService {
    @Autowired
    private ICommentService commentService;

    @Override
    public List<List<Comment>> loadSolutionComment(Long solutionId) {
        List<Comment> list = commentService.list(new QueryWrapper<Comment>()
                .eq("solution_id", solutionId)
                .eq("status", true)
                .isNull("floor_id")
                .orderByDesc("create_time"));
        List<List<Comment>> comments = new ArrayList<>(list.size());
        list.stream().forEach((item) -> {
            List<Comment> floor = null;
            item.setFloorId(item.getId());
            List<Comment> floorItems = commentService.list(new QueryWrapper<Comment>()
                    .eq("floor_id", item.getId())
                    .eq("status", true));
            floor = new ArrayList<>(floorItems.size() + 1);
            floor.add(item);
            floor.addAll(floorItems);
            comments.add(floor);
        });
        return comments;
    }

    @Override
    public CommonResult makeComment(Comment comment) {
        Long uid = UserInfoUtil.getUid();
        LocalDateTime now = LocalDateTime.now();
        comment.setCreateTime(now);
        comment.setUid(uid);
        return commentService.save(comment) ?
                CommonResult.success(comment.getParentId() != null ? "回复成功" : "评论成功") :
                CommonResult.error("回复失败");
    }

    @Override
    public CommonResult deleteComment(Long commentId) {
        Long uid = UserInfoUtil.getUid();
        Comment one = commentService.getOne(new QueryWrapper<Comment>().eq("id", commentId).eq("uid", uid));
        if (one == null) {
            return CommonResult.permissionDeny("非法访问");
        }
        return commentService.removeById(commentId) ? CommonResult.success("删除成功") : CommonResult.error("删除失败");
    }
}
