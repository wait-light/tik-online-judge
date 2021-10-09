package top.adxd.tikonlinejudge.user.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * @author light
 */
public interface IMailService {
    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    CommonResult sendSimpleMail(String to, String subject, String content);
    CommonResult sendCode(String to);

}
