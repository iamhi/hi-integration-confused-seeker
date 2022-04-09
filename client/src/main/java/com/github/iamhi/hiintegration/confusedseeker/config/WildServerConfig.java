package com.github.iamhi.hiintegration.confusedseeker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("wild.server")
@Data
public class WildServerConfig {

    private String url;

    private String port;

    private String websocketSuffix;
}
