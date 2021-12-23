import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static String reg = "((#+ )\\S+\\s?)|(```\\S+\\s([^`]*)```)|(!?\\[.*\\]\\(.+\\))";

    public static void main(String[] args) {
        String a = "# 阿萨德\n" +
                "![](https://tik-online-judge.oss-cn-hangzhou.aliyuncs.com/2021-12-23/bba31412-51a2-47bf-ae7a-4b74cd7b0d40_wolfgang-hasselmann-WrVvYxq11Yk-unsplash.jpg)\n" +
                "\n" +
                "## 奥术大师多\n" +
                "## 到了\n" +
                "# 奥术大师多\n" +
                "## 奥术大师多";
        System.out.println(a.replaceAll(reg,""));
    }
}
