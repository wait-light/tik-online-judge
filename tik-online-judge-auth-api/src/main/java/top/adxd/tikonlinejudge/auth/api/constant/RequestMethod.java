package top.adxd.tikonlinejudge.auth.api.constant;

public class RequestMethod {
    private RequestMethod(){
        throw new UnsupportedOperationException("不允许初始化");
    }
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    public static final String OPTION = "OPTION";
    public static final String PUT = "PUT";

}
