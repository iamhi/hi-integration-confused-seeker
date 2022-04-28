package com.github.iamhi.hiintegration.confusedseeker.in;

public record EchoMessageHandler() implements MessageHandler {
    @Override
    public String handle(String message) {
        System.out.println(message);

        return message;
    }
}
