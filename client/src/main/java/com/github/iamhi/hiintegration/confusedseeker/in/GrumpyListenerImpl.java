package com.github.iamhi.hiintegration.confusedseeker.in;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class GrumpyListenerImpl implements GrumpyListener {

    private MessageHandler messageHandler = new EchoMessageHandler();

    @Override
    public Mono<Void> hearMeOut(Flux<WebSocketMessage> messages) {
        return messages
            .map(WebSocketMessage::getPayloadAsText)
            .map(messageHandler::handle)
            .then(Mono.empty());
    }

    @Override
    public void attachHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }
}
