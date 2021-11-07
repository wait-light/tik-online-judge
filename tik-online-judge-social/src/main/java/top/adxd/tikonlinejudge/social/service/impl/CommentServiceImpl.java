package top.adxd.tikonlinejudge.social.service.impl;

import top.adxd.tikonlinejudge.social.entity.Comment;
import top.adxd.tikonlinejudge.social.mapper.CommentMapper;
import top.adxd.tikonlinejudge.social.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
