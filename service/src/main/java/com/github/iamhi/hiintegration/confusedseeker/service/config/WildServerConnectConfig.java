package com.github.iamhi.hiintegration.confusedseeker.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("wild.server.channel.default")
@Data
public class WildServerConnectConfig {

    String listen;

    String send;
}