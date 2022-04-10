package com.github.iamhi.hiintegration.confusedseeker.core;

import com.github.iamhi.hiintegration.confusedseeker.config.WildServerConfig;
import com.github.iamhi.hiintegration.confusedseeker.in.GrumpyListener;
import com.github.iamhi.hiintegration.confusedseeker.out.CarelessSender;
import com.github.iamhi.hiintegration.confusedseeker.out.CreepyConnector;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
record MadOrganizerImpl(
    CarelessSender carelessSender,
    CreepyConnector creepyConnector,
    GrumpyListener grumpyListener,
    WildServerConfig wildServerConfig,
    ForgetfulState forgetfulState
) implements MadOrganizer {

    @Override
    public Mono<Void> start() {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI uri = URI.create("ws://"
            + wildServerConfig.getUrl()
            + ":"
            + wildServerConfig.getPort()
            + wildServerConfig.getWebsocketSuffix());

        return creepyConnector.getToken().map(token -> {
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("connect-token", token);

            return httpHeaders;
        }).flatMap(httpHeaders -> client.execute(uri, httpHeaders, webSocketSession ->
            webSocketSession
                .send(carelessSender.supply().map(webSocketSession::textMessage))
                .and(grumpyListener.hearMeOut(webSocketSession.receive()))));
    }
}
