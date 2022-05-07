package com.github.iamhi.hiintegration.confusedseeker.core;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class ForgetfulStateImpl implements ForgetfulState {

    Map<String, String> tokenMap = new HashMap<>();

    Map<String, String> hashHashSecret = new HashMap<>();

    MultiValueMap<String, Consumer<String>> tokenConsumers = new LinkedMultiValueMap<>();

    @Override
    public String getToken(String destination) {
        return tokenMap.get(destination);
    }

    @Override
    public void addSecret(String destination, String secret) {
        hashHashSecret.put(destination, secret);
    }

    @Override
    public String getSecret(String destination) {
        return hashHashSecret.get(destination);
    }

    @Override
    public String setToken(String destination, String token) {
        tokenMap.put(destination, token);

        tokenConsumers.computeIfPresent(destination, (key, list) -> {
            list.forEach(tokenConsumer -> tokenConsumer.accept(token));

            return new ArrayList<>();
        });

        return token;
    }

    @Override
    public void addTokenObserver(String destination, Consumer<String> getToken) {
        if (!"".equals(tokenMap.get(destination))) {
            getToken.accept(tokenMap.get(destination));
        } else {
            tokenConsumers.add(destination, getToken);
        }
    }
}
