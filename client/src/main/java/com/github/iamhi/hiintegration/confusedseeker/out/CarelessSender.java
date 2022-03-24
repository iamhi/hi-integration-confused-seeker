package com.github.iamhi.hiintegration.confusedseeker.out;

import reactor.core.publisher.Flux;

public interface CarelessSender {

    void yeet(String message);

    Flux<String> supply();
}
