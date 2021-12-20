package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Comment;

import java.util.List;

public interface ISolutionCommentSolutionService {
    List<List<Comment>> loadSolutionComment(Long solutionId);
    CommonResult makeComment(Comment comment);
    CommonResult deleteComment(Long commentId);
}
