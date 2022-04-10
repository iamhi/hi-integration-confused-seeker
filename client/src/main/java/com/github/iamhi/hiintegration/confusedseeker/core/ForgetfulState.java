package com.github.iamhi.hiintegration.confusedseeker.core;

import java.util.function.Consumer;

public interface ForgetfulState {

    String setToken(String token);

    String getToken();

    void addTokenObserver(Consumer<String> getToken);
}
