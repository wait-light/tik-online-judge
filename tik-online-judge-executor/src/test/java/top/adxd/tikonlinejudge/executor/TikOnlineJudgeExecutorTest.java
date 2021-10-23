package top.adxd.tikonlinejudge.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.ICodeJudge;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wait_light
 * @create 2021/9/9
 */
@SpringBootTest
@Slf4j
public class TikOnlineJudgeExecutorTest {


    @Test
    public void context2() throws ExecutionException, InterruptedException {
//        pythonCodeExecutor.execute(null);

//        ExecuteResult execute = cppCodeExecutor.execute(null);
//        ExecuteResult execute = cLanguageCodeExecutor.execute(null);
//        CompletableFuture<ExecuteResult> executeResultCompletableFuture1 = javaCodeExecutor.executeAsync(null);
//        ExecuteResult executeResult = executeResultCompletableFuture1.get();
//        CompletableFuture<ExecuteResult> executeResultCompletableFuture = javaCodeExecutor.executeAsync(null);
//        System.out.println(executeResult.toString());
    }

    final class aaaaa{

    }



    @Test
    public void context() throws ExecutionException, InterruptedException {
//        CmdExecutor2 build = new CmdExecutor2.CmdExecutorBuilder()
//                .setDir(new File("C:\\Users\\wait_light\\Desktop"))
//                .setCmdWithArgs("cmd.exe /c compile.cmd")
//                .build();
//        build.get();
        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
                .setDir(new File("D:\\study\\spring\\cloud\\project\\tik-online-judge\\tik-online-judge-executor\\code\\cpp"))
//                .setEnvp(new String[]{"g++=D:\\soft\\MinGw\\bin\\g++.exe"})
                .setCmdWithArgs("D:/soft/MinGw/bin/g++.exe main.cpp")
//                .setCmdWithArgs("C:\\Users\\wait_light\\Desktop\\a.exe")
                .build();
        ExecuteCMDResult executeCMDResult = build.get();
        System.out.println(executeCMDResult.toString());
//        System.out.println(executeCMDResult.toString());
//        Runtime.getRuntime().exec("D:/soft/MinGw/bin/g++.exe C:\\\\Users\\\\wait_light\\\\Desktop\\\\main.cpp",null,new File())
//        ExecuteCMDResult executeCMDResult = build.get();
////        Runtime.getRuntime().exec()
//        System.out.println(executeCMDResult);
//        ExecuteResult execute = javaCodeExecutor.execute(null);
//        System.out.println(execute);
//        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
//                .setCmdWithArgs("python 1 + 2")
//                .build();
//        ExecuteCMDResult executeCMDResult = build.get();
//        System.out.println(executeCMDResult);
    }

    @Test
    public void systemMessageTest() {
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
                .command("cmd", "/c", "a.exe")
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
        while ((len = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
//        while (a.waitFor()){
//
//        }

        System.out.println(a.waitFor());
    }


    @Test
    public void cmdTest() {
        try {
            // 执行指令
            final Process process = Runtime.getRuntime().exec("D:\\soft\\MinGw\\bin\\g++.exe main.cpp", null, new File("C:\\Users\\wait_light\\Desktop"));
//            final Process process = Runtime.getRuntime().exec("echo 1 + 2", null, new File("C:\\Users\\wait_light\\Desktop"));
            if (process != null) {
                byte[] bytes = new byte[1024];
                int len = -1;

                boolean running = process.waitFor(10,TimeUnit.SECONDS);
                System.out.println(process.exitValue());
                // 获取输出
                InputStream inputStream = process.getInputStream();
                while ((len = inputStream.read(bytes)) != -1) {
                    System.out.println((new String(bytes, 0, len, Charset.forName("gbk"))));
                }
                inputStream.close();
                // 获取错误输出
                InputStream errorStream = process.getErrorStream();
                len = -1;
                while ((len = errorStream.read(bytes)) != -1) {
                    System.out.println((new String(bytes, 0, len, Charset.forName("gbk"))));
                }
                errorStream.close();
                System.out.println("退出码：" + process.exitValue());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
