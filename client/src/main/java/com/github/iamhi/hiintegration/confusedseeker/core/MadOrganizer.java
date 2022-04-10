package com.github.iamhi.hiintegration.confusedseeker.core;

import reactor.core.publisher.Mono;

public interface MadOrganizer {

    Mono<Void> start();
}
