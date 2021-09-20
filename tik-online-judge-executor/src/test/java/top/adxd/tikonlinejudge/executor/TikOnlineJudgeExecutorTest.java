package top.adxd.tikonlinejudge.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.executor.service.impl.CPPCodeExecutor;
import top.adxd.tikonlinejudge.executor.service.impl.JavaCodeExecutor;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author wait_light
 * @create 2021/9/9
 */
//@SpringBootTest
@Slf4j
public class TikOnlineJudgeExecutorTest {
    @Autowired
    private CPPCodeExecutor javaCodeExecutor;
    @Test
    public void context() throws ExecutionException, InterruptedException {
        ExecuteResult execute = javaCodeExecutor.execute(null);
        System.out.println(execute);
    }

    @Test
    public void systemMessageTest(){
        System.out.println(0 ^ 5);
    }
    @Test
    public void builderTest() throws IOException, InterruptedException {
        File input = new File("C:\\Users\\light\\Desktop\\cpptest\\hahahah.txt");
        File error = new File("C:\\Users\\light\\Desktop\\cpptest\\error.txt");
//        File aaaaa = new File("C:\\Users\\light\\Desktop\\cpptest\\a.exe");
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process a = processBuilder
                .directory(new File("C:\\Users\\light\\Desktop\\cpptest"))
                .command("cmd","/c","a.exe")
                .redirectOutput(new File("C:\\Users\\light\\Desktop\\cpptest\\hahahah.txt"))
                .redirectError(new File("C:\\Users\\light\\Desktop\\cpptest\\error.txt"))
                .start();

//        System.out.println(aaaaa.canExecute());
//        Process a = processBuilder
//                .directory(new File("D:\\study\\spring\\cloud\\project\\tik-online-judge\\tik-online-judge-executor\\classTarget"))
////                .command("cmd","/c","a.exe")
//                .command("java","Main")
//                .redirectInput(input)
//                .redirectError(error)
//                .start();
        InputStream inputStream = a.getInputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        while ( (len = inputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }
//        while (a.waitFor()){
//
//        }

        System.out.println(   a.waitFor());
    }
}
