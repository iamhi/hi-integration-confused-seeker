package com.github.iamhi.hiintegration.confusedseeker.out;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

@Service
class CarelessSenderImpl implements CarelessSender {

    private final Queue<String> buffer = new LinkedList<>();
    private Consumer<String> emitter = message -> {
    };

    @Override
    public String yeet(String message) {
        buffer.add(message);

        emitter.accept(message);

        return message;
    }

    @Override
    public Flux<String> supply() {
        return Flux.create(sink -> {
            this.emitter = sink::next;
        });
    }
}
