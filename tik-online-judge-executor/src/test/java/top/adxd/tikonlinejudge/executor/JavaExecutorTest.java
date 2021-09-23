package top.adxd.tikonlinejudge.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

//@SpringBootTest
@Slf4j
public class JavaExecutorTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
    @Test
    public void aPlusB(){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }

    @Test
    public void endWith(){
        String testString = "\r\n";
        System.out.println(testString.length());
    }
}
