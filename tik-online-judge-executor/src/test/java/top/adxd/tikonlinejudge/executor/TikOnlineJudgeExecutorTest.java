package top.adxd.tikonlinejudge.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author wait_light
 * @create 2021/9/9
 */
//@SpringBootTest
@Slf4j
public class TikOnlineJudgeExecutorTest {
    @Test
    public void context() {
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
                while ((len = is.read(result)) != -1) {
                    System.out.println(new String(result, 0, len));
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

    @Test
    public void systemMessageTest(){
        System.out.println(0 ^ 5);
    }
}
