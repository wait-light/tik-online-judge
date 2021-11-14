package top.adxd.tikonlinejudge.gateway.filter;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
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
public class DefaultFilter implements GlobalFilter, Ordered {

    @DubboReference
    private IAuthorizationService authorizationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> token = request.getHeaders().get("token");
        if (token != null && token.size() > 0) {
            System.out.println(Arrays.toString(token.toArray()));
            AuthorizationResult authorization = authorizationService.authorization(token.get(0), request.getPath().value(), RequestMethod.string2RequestMethods(request.getMethod().name()).get(0));
            System.out.println(authorization);
        }
        System.out.println(request.getPath() + " " + request.getMethod());
        AuthorizationResult authorization = authorizationService.authorization("asdasd", request.getPath().value(), RequestMethod.string2RequestMethods(request.getMethod().name()).get(0));
        System.out.println(authorization);
//        List<String> green = request.getQueryParams().get("green");
//        System.out.println(green);
//        if (green.size() >= 2){
////            System.out.println(test);
//            ServerHttpResponse response = exchange.getResponse();
//            Mono<Void> voidMono = response.setComplete();
//            return voidMono;
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10;
    }
}
