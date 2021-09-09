package top.adxd.tikonlinejudge.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.entity.UmsRoleMenu;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wait_light
 * @create 2021/9/6
 */
//@Slf4j
//@SpringBootTest
public class TikOnlineJudgeUserTests {
    @Test
    public void aaa() {
        try {
            final long timeout = 3000; // 限制的执行时间（毫秒）

            String cmd = "echo aaa";
            final long starttime = System.currentTimeMillis();
            final Process process = Runtime.getRuntime().exec(cmd); // 执行编译指令

            if (process != null) {
                InputStream is = process.getInputStream(); // 获取编译命令输出
                InputStream error = process.getErrorStream(); // 获取编译命令错误输出
                byte[] result = new byte[1024];
                int len = -1;
                while ( (len = is.read(result)) != -1 ){
                    System.out.println(new String(result,0,len));
                }
                new Thread() {
                    public void run() {
                        while (true) {
                            try {
                                sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (System.currentTimeMillis() - starttime > timeout) {
                                // 超时
                                process.destroy();
                            }
                        }
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
