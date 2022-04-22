package com.github.iamhi.hiintegration.confusedseeker.out;

import com.github.iamhi.hiintegration.confusedseeker.config.TokenConfig;
import com.github.iamhi.hiintegration.confusedseeker.config.WildServerConfig;
import com.github.iamhi.hiintegration.confusedseeker.core.ForgetfulState;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public record CreepyConnectorImpl(
    WebClient.Builder builder,
    WildServerConfig wildServerConfig,
    ForgetfulState forgetfulState,
    TokenConfig tokenConfig
) implements CreepyConnector {

    @Override
    public Mono<String> getToken() {
        WebClient webClient = getClient();

        return webClient.post().uri("/token")
            .bodyValue(new TokenRequest(tokenConfig.getSecret()))
            .retrieve()
            .bodyToMono(String.class)
            .map(forgetfulState::setToken);
    }

    @Override
    public Mono<Void> joinChannel(String channel) {
        WebClient webClient = getClient();

        forgetfulState.addTokenObserver(token -> webClient
            .post()
            .uri("/channel")
            .bodyValue(new JoinChannelRequest(forgetfulState.getToken(), channel))
            .retrieve().bodyToMono(String.class).subscribe());

        return Mono.empty();
    }

    private WebClient getClient() {
        return builder.baseUrl("http://" + wildServerConfig.getUrl() + ":" + wildServerConfig.getPort()).build();
    }
}
