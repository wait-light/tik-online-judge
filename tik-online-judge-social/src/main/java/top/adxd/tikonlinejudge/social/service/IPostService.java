package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Solution;

public interface IPostService {
    CommonResult post(Solution post);
    CommonResult updatePost(Solution post);
    CommonResult deletePost(Long postId);
    CommonResult postList(Integer type);
    CommonResult getPost(Long postId);
}
