package com.github.iamhi.hiintegration.confusedseeker.out;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Service
class CarelessSenderImpl implements CarelessSender {

    private Consumer<String> emitter = message -> {
    };

    @Override
    public String yeet(String message) {
        emitter.accept(message);

        return message;
    }

    @Override
    public Flux<String> supply() {
        return Flux.create(sink -> this.emitter = sink::next);
    }
}
