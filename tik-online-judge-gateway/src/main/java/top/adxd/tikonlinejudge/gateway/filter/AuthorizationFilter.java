package top.adxd.tikonlinejudge.gateway.filter;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.adxd.tikonlinejudge.auth.api.IAuthorizationService;
import top.adxd.tikonlinejudge.auth.api.dto.AuthorizationResult;
import top.adxd.tikonlinejudge.common.singleton.RequestMethod;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.Arrays;
import java.util.List;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @DubboReference
    private IAuthorizationService authorizationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getMethod() == HttpMethod.OPTIONS){
            return chain.filter(exchange);
        }
        System.out.println(request.getPath() + " " + request.getMethod());
        List<String> token = request.getHeaders().get("token");
        if (token != null && token.size() > 0) {
            AuthorizationResult authorization = authorizationService.authorization(token.get(0), request.getPath().value(), RequestMethod.string2RequestMethods(request.getMethod().name()).get(0));
            System.out.println(authorization);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10;
    }
}
