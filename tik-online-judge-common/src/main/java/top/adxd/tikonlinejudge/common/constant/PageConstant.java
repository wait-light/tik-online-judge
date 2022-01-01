package top.adxd.tikonlinejudge.common.constant;

/**
 * @author wait_light
 * @create 2021/9/6
 */
public class PageConstant {
    private PageConstant(){};
    //分页数据键
    public static final String LIST_KEY = "list";
    //是否有下一页
    public static final String HAS_NEXT_PAGE = "hasNextPage";
    //当前页码
    public static final String CURRENT_PAGE = "currentPage";
    //是否有上一页
    public static final String HAS_PREVIOUS_PAGE = "hasPreviousPage";
    //页面大小
    public static final String PAGE_SIZE = "pageSize";
    //总页数
    public static final String TOTAL_PAGE = "totalSize";
    //总记录数
    public static final String TOTAL = "total";

    /**
     * 以下常量为发起请求时，页面查询的变量
     */
    //查询的页
    public static final String PAGE_KEY = "page";
    //查询的页面大小
    public static final String PAGE_SIZE_KEY = "pageSize";
    //排序
    public static final String PAGE_ORDER_BY = "orderBy";
//    //升序降序
//    public static final String PAGE_ORDER_SEQUENCE = "sequence";
//    //升序
//    public static final String PAGE_ORDER_ASC = "asc";
//    //降序
//    public static final String PAGE_ORDER_DESC = "desc";
    //搜索列
    public static final String PAGE_SEARCH_KEY = "searchKey";
}
