public class Main {
    public static void main(String[] args) {
        System.out.println("编译测试");
        dfs(1);
    }
    public static void dfs(int floor){
        System.out.println("floor : " + floor);
        dfs(floor + 1);
    }
}