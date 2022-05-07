package com.github.iamhi.hiintegration.confusedseeker.core;

import java.util.function.Consumer;

public interface ForgetfulState {

    String setToken(String destination, String token);

    String getToken(String destination);

    void addSecret(String destination, String secret);

    String getSecret(String destination);

    void addTokenObserver(String destination, Consumer<String> getToken);
}
