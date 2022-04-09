package com.github.iamhi.hiintegration.confusedseeker.out;

import com.github.iamhi.hiintegration.confusedseeker.config.WildServerConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public record CreepyConnectorImpl(
    WebClient.Builder builder,
    WildServerConfig wildServerConfig
) implements CreepyConnector {

    @Override
    public Mono<String> getToken() {
        WebClient webClient = getClient();

        return webClient.get().uri("/token").retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<Void> joinChannel(String channel) {
        WebClient webClient = getClient();

        return webClient.post().uri("/channel").retrieve().bodyToMono(String.class).then();
    }

    private WebClient getClient() {
        return builder.baseUrl("http://" + wildServerConfig.getUrl() + ":" + wildServerConfig.getPort()).build();
    }
}
