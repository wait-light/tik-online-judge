package top.adxd.tikonlinejudge.common.filter;

import top.adxd.tikonlinejudge.common.util.PageUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 用于处理每次请求结束后的分页信息，避免影响下一次分页结果
 */
public class ClearPageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        PageUtils.clear();
    }
}
