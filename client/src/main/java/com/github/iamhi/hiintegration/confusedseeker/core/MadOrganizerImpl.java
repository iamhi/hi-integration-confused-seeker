package com.github.iamhi.hiintegration.confusedseeker.core;

import com.github.iamhi.hiintegration.confusedseeker.config.WildServerConfig;
import com.github.iamhi.hiintegration.confusedseeker.in.GrumpyListener;
import com.github.iamhi.hiintegration.confusedseeker.out.CarelessSender;
import com.github.iamhi.hiintegration.confusedseeker.out.CreepyConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
class MadOrganizerImpl implements MadOrganizer {

    final CarelessSender carelessSender;

    final CreepyConnector creepyConnector;

    final GrumpyListener grumpyListener;

    final WildServerConfig wildServerConfig;

    final ForgetfulState forgetfulState;

    private Consumer<String> connectionEmitter = message -> {
    };

    @Override
    public Flux<Void> start() {
        return Flux.create(fluxSink -> {
            connectionEmitter = fluxSink::next;
        }).cast(String.class).flatMap(this::establishConnection);
    }

    @Override
    public Mono<Void> connect(String destination) {
        connectionEmitter.accept(destination);

        return Mono.empty();
    }

    private Mono<Void> establishConnection(String destination) {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI uri = URI.create("ws://" + destination + "/ws/basic");

        return creepyConnector.getToken(destination).map(token -> {
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("connect-token", token);

            return httpHeaders;
        }).flatMap(httpHeaders -> client.execute(uri, httpHeaders, webSocketSession -> webSocketSession
            .send(carelessSender.supply().map(webSocketSession::textMessage))
            .and(grumpyListener.hearMeOut(webSocketSession.receive()))));
    }
}
