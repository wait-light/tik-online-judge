package top.adxd.tikonlinejudge.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;
import top.adxd.tikonlinejudge.common.constant.PageConstant;

import java.util.Map;

/**
 * @author wait_light
 * @create 2021/9/6
 */
public class PageUtils {
    protected static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal();

    //从request中获取分页参数，同时利用pageHelper
    public static <E> Page<E> makePage() {
        return makePage(true);
    }

    //从request中获取分页参数，同时利用pageHelper
    public static <E> Page<E> makePage(boolean order) {
        Integer page = ServletUtils.getParameterToInt(PageConstant.PAGE_KEY);
        if (page == null || page <= 0) {
            page = 1;
        }
        Integer pageSize = ServletUtils.getParameterToInt(PageConstant.PAGE_SIZE_KEY);
        //页面大小限制
        if (pageSize == null || pageSize <= 0 || pageSize > 100) {
            pageSize = 10;
        }
        if (order) {
            //注入处理
            String orderBy = SqlUtil.escapeOrderBySql(ServletUtils.getParameter(PageConstant.PAGE_ORDER_BY));
            if (StringUtils.hasLength(orderBy)) {
                Page<E> pageInfo = PageHelper.startPage(page, pageSize, orderBy);
                LOCAL_PAGE.set(pageInfo);
                return pageInfo;
            }
        }
        Page<E> pageInfo = PageHelper.startPage(page, pageSize);
        LOCAL_PAGE.set(pageInfo);
        return pageInfo;
    }

    public static void MakeOrder() {
        String orderBy = SqlUtil.escapeOrderBySql(ServletUtils.getParameter(PageConstant.PAGE_ORDER_BY));
        if (StringUtils.hasLength(orderBy)) {
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 获取makePage的页面信息
     *
     * @param <E>
     * @return
     */

    public static <E> Page<E> pageInfo() {
        Page page = LOCAL_PAGE.get();
        return page;
    }

    /**
     * 移除page信息
     */
    public static void clear() {
        LOCAL_PAGE.remove();
    }
}
