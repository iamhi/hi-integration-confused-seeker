package com.github.iamhi.hiintegration.confusedseeker.core;

import com.github.iamhi.hiintegration.confusedseeker.config.WildServerConfig;
import com.github.iamhi.hiintegration.confusedseeker.in.GrumpyListener;
import com.github.iamhi.hiintegration.confusedseeker.out.CarelessSender;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;

public record MadOrganizerImpl(
    WildServerConfig wildServerConfig
) implements MadOrganizer {

    @Override
    public void start(GrumpyListener grumpyListener, CarelessSender carelessSender) {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI uri = URI.create("ws://"
            + wildServerConfig.getUrl()
            + ":"
            + wildServerConfig.getPort()
            + wildServerConfig.getWebsocketSuffix());


        client.execute(uri, webSocketSession ->
            webSocketSession
                .send(carelessSender.supply().map(webSocketSession::textMessage))
                .and(grumpyListener.hearMeOut(webSocketSession.receive()))).subscribe();
    }
}
