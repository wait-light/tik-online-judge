package top.adxd.tikonlinejudge.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Comment;
import top.adxd.tikonlinejudge.social.service.ISolutionCommentSolutionService;

import java.util.List;

@RestController
@RequestMapping("/social/solution/comment/{solutionId}")
public class SolutionCommentController {
    @Autowired
    private ISolutionCommentSolutionService solutionCommentSolutionService;

    @GetMapping("/floor")
    public CommonResult commentFloors(@PathVariable("solutionId") Long solutionId) {
        List<List<Comment>> lists = solutionCommentSolutionService.loadSolutionComment(solutionId);
        return CommonResult.success().singleData(lists);
    }

    @DeleteMapping("/{commentId}")
    public CommonResult deleteComment(@PathVariable("commentId") Long commentId){
        return solutionCommentSolutionService.deleteComment(commentId);
    }

    @PostMapping("")
    public CommonResult makeComment(@RequestBody @Validated Comment comment,@PathVariable("solutionId")Long solutionId){
        comment.setSolutionId(solutionId);
        return solutionCommentSolutionService.makeComment(comment);
    }
}
