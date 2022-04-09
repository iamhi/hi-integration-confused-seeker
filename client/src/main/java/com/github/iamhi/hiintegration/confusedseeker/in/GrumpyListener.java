package com.github.iamhi.hiintegration.confusedseeker.in;

import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GrumpyListener {

    Mono<Void> hearMeOut(Flux<WebSocketMessage> messages);

    void attachHandler(MessageHandler handler);
}
