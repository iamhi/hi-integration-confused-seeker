package com.github.iamhi.hiintegration.confusedseeker.service.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GenericRouter {

    private static final String ROUTER_PREFIX = "";

    @Bean
    public RouterFunction<ServerResponse> composeRoute(GenericHandler genericHandler) {
        return route(GET(ROUTER_PREFIX + "/ping"), serverRequest ->
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Map.of("ping", "PONG from Confused seeker")), Map.class))
            .andRoute(POST(ROUTER_PREFIX + "/message"), genericHandler::sendMessage);
    }
}
