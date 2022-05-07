package com.github.iamhi.hiintegration.confusedseeker.service.in;

import com.github.iamhi.hiintegration.confusedseeker.core.ForgetfulState;
import com.github.iamhi.hiintegration.confusedseeker.core.MadOrganizer;
import com.github.iamhi.hiintegration.confusedseeker.out.CarelessSender;
import com.github.iamhi.hiintegration.confusedseeker.out.CreepyConnector;
import com.github.iamhi.hiintegration.confusedseeker.service.api.ServerConnectRequest;
import com.github.iamhi.hiintegration.confusedseeker.service.api.ServerJoinChannelRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public record GenericHandler(
    CarelessSender carelessSender,
    MadOrganizer madOrganizer,
    ForgetfulState forgetfulState,
    CreepyConnector creepyConnector
) {
    Mono<ServerResponse> sendMessage(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(String.class).map(carelessSender::yeet).then(ServerResponse.ok().bodyValue("ok"));
    }

    Mono<ServerResponse> connect(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ServerConnectRequest.class)
            .map(serverConnectRequest -> {
                if (StringUtils.isNotBlank(serverConnectRequest.secret())) {
                    forgetfulState.addSecret(serverConnectRequest.destination(), serverConnectRequest.secret());
                }

                return serverConnectRequest.destination();
            })
            .flatMap(madOrganizer::connect).then(ServerResponse.ok().bodyValue("ok"));
    }

    Mono<ServerResponse> joinChannel(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ServerJoinChannelRequest.class)
            .flatMap(joinChannelRequest ->
                creepyConnector.joinChannel(joinChannelRequest.destination(), joinChannelRequest.channel()))
            .then(ServerResponse.ok().bodyValue("ok"));
    }
}
