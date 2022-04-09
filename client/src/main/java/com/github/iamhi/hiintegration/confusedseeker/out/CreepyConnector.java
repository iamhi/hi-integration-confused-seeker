package com.github.iamhi.hiintegration.confusedseeker.out;

import reactor.core.publisher.Mono;

public interface CreepyConnector {

    Mono<String> getToken();

    Mono<Void> joinChannel(String channel);
}
