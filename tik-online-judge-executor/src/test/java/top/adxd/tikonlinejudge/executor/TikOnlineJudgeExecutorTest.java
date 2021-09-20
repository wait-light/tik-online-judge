package top.adxd.tikonlinejudge.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.impl.CPPCodeExecutor;
import top.adxd.tikonlinejudge.executor.service.impl.JavaCodeExecutor;
import top.adxd.tikonlinejudge.executor.service.impl.PythonCodeExecutor;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wait_light
 * @create 2021/9/9
 */
@SpringBootTest
@Slf4j
public class TikOnlineJudgeExecutorTest {
    @Autowired
    private JavaCodeExecutor javaCodeExecutor;
    @Autowired
    private CPPCodeExecutor cppCodeExecutor;
    @Autowired
    private PythonCodeExecutor pythonCodeExecutor;

    @Test
    public void context2() throws ExecutionException, InterruptedException {
//        pythonCodeExecutor.execute(null);

//        ExecuteResult execute = cppCodeExecutor.execute(null);
        ExecuteResult execute = pythonCodeExecutor.execute(null);
        System.out.println(execute.toString());
    }

    @Test
    public void test(){

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
                .setDir(new File("C:\\Users\\wait_light\\Desktop"))
//                .setEnvp(new String[]{"g++=D:\\soft\\MinGw\\bin\\g++.exe"})
//                .setCmdWithArgs("D:/soft/MinGw/bin/g++.exe main.cpp")
                .setCmdWithArgs("C:\\Users\\wait_light\\Desktop\\a.exe")
                .build();
        ExecuteCMDResult executeCMDResult = build.get();
        System.out.println(executeCMDResult.toString());
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
    public void sortTest() {
//        int[] x = new int[]{884688278, 387052570, 77481420, 537616843, 659978110, 215386675, 604354651, 64838842, 830623614, 544526209, 292446044, 570872277, 946770900, 411203381, 445747969, 480363996, 31693639, 303753633, 261627544, 884642435, 978672815, 427529125, 111935818, 109686701, 714012242, 691252458, 230964510, 340316511, 917959651, 544069623, 193715454, 631219735, 219297819, 151485185, 986263711, 805374069, 915272981, 493886311, 970466103, 819959858, 733048764, 393354006, 631784130, 70309112, 513023688, 17092337, 698504118, 937296273, 54807658, 353487181, 82447697, 177571868, 830140516, 536343860, 453463919, 998857732, 280992325, 13701823, 728999048, 764532283, 693597252, 433183457, 157540946, 427514727, 768122842, 782703840, 965184299, 586696306, 256184773, 984427390, 695760794, 738644784, 784607555, 433518449, 440403918, 281397572, 546931356, 995773975, 738026287, 861262547, 119093579, 521612397, 306242389, 84356804, 42607214, 462370265, 294497342, 241316335, 158982405, 970050582, 740856884, 784337461, 885254231, 633020080, 641532230, 421701576, 298738196, 918973856, 472147510, 169670404};
        int[] x = new int[]{5, 2, 3, 1, 4, 0, 10};
        System.out.println(Arrays.toString(MySort(x)));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     *
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort(int[] arr) {
        // write code here
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int target = arr[left];
        int L = left + 1, R = right;
        while (L <= R) {
            //寻找比目标大的数
            while (L < right && arr[L] <= target) {
                L++;
            }
            //寻找比目标小的数
            while (R > left && arr[R] >= target) {
                R--;
            }
            if (L >= R) {
                break;
            }
            if (arr[L] > arr[R]) {
                int temp = arr[L];
                arr[L] = arr[R];
                arr[R] = temp;
            }
        }
        arr[left] = arr[R];
        arr[R] = target;
        //以目标数为分界线，计算两边
        quickSort(arr, left, R - 1);
        quickSort(arr, R + 1, right);
    }


    @Test
    public void cmdTest() {
        try {
            // 执行指令
            final Process process = Runtime.getRuntime().exec("D:\\soft\\MinGw\\bin\\g++ main.cpp", null, new File("C:\\Users\\wait_light\\Desktop"));
            if (process != null) {
                byte[] bytes = new byte[1024];
                int len = -1;
                process.waitFor(10, TimeUnit.SECONDS);
                // 获取错误输出
                InputStream errorStream = process.getErrorStream();
                len = -1;
                while ((len = errorStream.read(bytes)) != -1) {
                    System.out.println((new String(bytes, 0, len, Charset.forName("gbk"))));
                }
                errorStream.close();
                // 获取输出
                InputStream inputStream = process.getInputStream();
                while ((len = inputStream.read(bytes)) != -1) {
                    System.out.println((new String(bytes, 0, len, Charset.forName("gbk"))));
                }
                inputStream.close();
                System.out.println("退出码：" + process.exitValue());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
