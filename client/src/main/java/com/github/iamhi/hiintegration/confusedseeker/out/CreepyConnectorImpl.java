package com.github.iamhi.hiintegration.confusedseeker.out;

import com.github.iamhi.hiintegration.confusedseeker.core.ForgetfulState;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
record CreepyConnectorImpl(
    WebClient.Builder builder,
    ForgetfulState forgetfulState
) implements CreepyConnector {

    @Override
    public Mono<String> getToken(String destination) {
        WebClient webClient = getClient(destination);

        return webClient.post().uri("/token")
            .bodyValue(new TokenRequest(forgetfulState.getSecret(destination)))
            .retrieve()
            .bodyToMono(String.class)
            .map(token -> forgetfulState.setToken(destination, token));
    }

    @Override
    public Mono<Void> joinChannel(String destination, String channel) {
        WebClient webClient = getClient(destination);

        forgetfulState.addTokenObserver(destination, token -> webClient
            .post()
            .uri("/channel")
            .bodyValue(new JoinChannelRequest(forgetfulState.getToken(destination), channel))
            .retrieve().bodyToMono(String.class).subscribe());

        return Mono.empty();
    }

    private WebClient getClient(String destination) {
        return builder.baseUrl("http://" + destination).build();
    }
}
