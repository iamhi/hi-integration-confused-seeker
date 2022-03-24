package com.github.iamhi.hiintegration.confusedseeker.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("wild.server")
@Data
public class WildServerConfig {

    String url;

    String port;

    @Value("websocket.suffix")
    String websocketSuffix;
}
