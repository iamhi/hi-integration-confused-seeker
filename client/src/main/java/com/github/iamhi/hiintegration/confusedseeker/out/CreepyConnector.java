package com.github.iamhi.hiintegration.confusedseeker.out;

import reactor.core.publisher.Mono;

public interface CreepyConnector {

    Mono<String> getToken(String destination);

    Mono<Void> joinChannel(String destination, String channel);
}
