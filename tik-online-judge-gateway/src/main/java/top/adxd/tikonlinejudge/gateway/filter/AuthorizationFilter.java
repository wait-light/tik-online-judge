package top.adxd.tikonlinejudge.gateway.filter;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.adxd.tikonlinejudge.auth.api.IAuthorizationService;
import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.api.dto.AuthorizationResult;

import java.util.List;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @DubboReference
    private IAuthorizationService authorizationService;
    public static final String UID_HEADER = "uid";
    public static final String TOKEN_HEADER = "token";
    public static final String ADMIN_HEADER = "isAdmin";

    public static void main(String[] args) throws Exception {
        throw new Exception("trying throw");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }
        List<String> token = request.getHeaders().get(TOKEN_HEADER);
        AuthorizationResult authorization = authorizationService
                .authorization(token != null && token.size() > 0 ? token.get(0) : null
                        , request.getPath().value()
                        , RequestMethod.string2RequestMethods(request.getMethod().name()).get(0));
        //认证不通过，提前结束
        if (!authorization.success) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        //附加用户信息
        HttpHeaders headers = request.getHeaders();
        if (authorization.getUid() != null) {
            ServerHttpRequest newRequest = exchange
                    .getRequest()
                    .mutate()
                    .header(UID_HEADER, authorization.getUid().toString())
                    .header(ADMIN_HEADER, authorization.isAdmin().toString())
                    .build();
            exchange = exchange.mutate().request(newRequest).build();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10;
    }
}
