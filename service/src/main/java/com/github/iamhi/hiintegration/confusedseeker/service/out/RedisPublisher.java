package com.github.iamhi.hiintegration.confusedseeker.service.out;

import reactor.core.publisher.Mono;

public interface RedisPublisher {

    Mono<Long> publish(String channel, String message);
}
