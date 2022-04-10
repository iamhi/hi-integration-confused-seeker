package com.github.iamhi.hiintegration.confusedseeker.core;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class ForgetfulStateImpl implements ForgetfulState {

    String token = "";

    List<Consumer<String>> tokenConsumers = new ArrayList<>();

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String setToken(String token) {
        this.token = token;

        tokenConsumers.forEach(tokenConsumer -> tokenConsumer.accept(token));
        tokenConsumers.clear();

        return token;
    }

    @Override
    public void addTokenObserver(Consumer<String> getToken) {
        if (!"".equals(token)) {
            getToken.accept(token);
        } else {
            tokenConsumers.add(getToken);
        }
    }
}
