package top.adxd.tikonlinejudge.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Component
public class DefaultFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> green = request.getQueryParams().get("green");
        System.out.println(green);
        if (green.size() >= 2){
//            System.out.println(test);
            ServerHttpResponse response = exchange.getResponse();
            Mono<Void> voidMono = response.setComplete();
            return voidMono;
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10;
    }
}
