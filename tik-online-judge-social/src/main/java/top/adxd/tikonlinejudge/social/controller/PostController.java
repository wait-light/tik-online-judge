package top.adxd.tikonlinejudge.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Solution;
import top.adxd.tikonlinejudge.social.service.IPostService;

@RestController
@RequestMapping("/social/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping("/list")
    public CommonResult list(@RequestParam("type") Integer type) {
        return postService.postList(type);
    }

    @GetMapping("/{postId}")
    public CommonResult post(@PathVariable("postId") Long postId){
        return postService.getPost(postId);
    }

    @PostMapping("")
    public CommonResult post(@RequestBody @Validated Solution solution) {
        return postService.post(solution);
    }

    @PutMapping("")
    public CommonResult postUpdate(@RequestBody @Validated Solution solution) {
        return postService.updatePost(solution);
    }


    @DeleteMapping("/{postId}")
    public CommonResult delete(@PathVariable("postId") Long postId) {
        return postService.deletePost(postId);
    }
}
