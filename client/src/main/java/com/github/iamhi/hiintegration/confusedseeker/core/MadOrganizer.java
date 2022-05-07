package com.github.iamhi.hiintegration.confusedseeker.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MadOrganizer {

    Flux<Void> start();

    Mono<Void> connect(String destination);
}
