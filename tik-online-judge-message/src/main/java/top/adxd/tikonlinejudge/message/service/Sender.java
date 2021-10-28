package top.adxd.tikonlinejudge.message.service;
/*
 * @author  wait-light
 * @date  2021/10/28 下午1:27
 */

public interface Sender {
    void send(String to,String subject,String content);
}
