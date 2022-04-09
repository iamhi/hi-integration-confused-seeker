package com.github.iamhi.hiintegration.confusedseeker.service.gateway;

import com.github.iamhi.hiintegration.confusedseeker.out.CarelessSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public record GenericHandler(
    CarelessSender carelessSender
) {
    Mono<ServerResponse> sendMessage(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(String.class).map(carelessSender::yeet).then(ServerResponse.ok().bodyValue("ok"));
    }
}
