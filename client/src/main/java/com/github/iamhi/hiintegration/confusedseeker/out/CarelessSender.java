package com.github.iamhi.hiintegration.confusedseeker.out;

import reactor.core.publisher.Flux;

public interface CarelessSender {

    String yeet(String message);

    Flux<String> supply();
}
