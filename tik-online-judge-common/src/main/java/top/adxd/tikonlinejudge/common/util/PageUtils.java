package top.adxd.tikonlinejudge.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import top.adxd.tikonlinejudge.common.constant.PageConstant;

/**
 * @author wait_light
 * @create 2021/9/6
 */
public class PageUtils {
    //从request中获取分页参数，同时利用pageHelper
    public static <E> Page<E> makePage() {
        Integer page = ServletUtils.getParameterToInt(PageConstant.PAGE_KEY);
        if (page == null || page <= 0) {
            page = 1;
        }
        Integer pageSize = ServletUtils.getParameterToInt(PageConstant.PAGE_SIZE_KEY);
        //页面大小限制
        if (pageSize == null || pageSize <= 0 || pageSize > 100) {
            pageSize = 10;
        }
        //注入处理
        String orderBy = SqlUtil.escapeOrderBySql(ServletUtils.getParameter(PageConstant.PAGE_ORDER_BY));
        if (StringUtils.hasLength(orderBy)) {
            return PageHelper.startPage(page, pageSize, orderBy);
        } else {
            return PageHelper.startPage(page, pageSize);
        }
    }
}
