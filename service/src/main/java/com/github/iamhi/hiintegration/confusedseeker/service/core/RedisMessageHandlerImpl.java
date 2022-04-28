package com.github.iamhi.hiintegration.confusedseeker.service.core;

import com.github.iamhi.hiintegration.confusedseeker.service.config.RedisConfig;
import com.github.iamhi.hiintegration.confusedseeker.service.out.RedisPublisher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public record RedisMessageHandlerImpl(
    RedisPublisher redisPublisher,
    RedisConfig redisConfig
) implements RedisMessageHandler {

    private static final String DEFAULT_EXPORT_CHANNEL = "hi-integration-message-export";

    @Override
    public String handle(String message) {

        redisPublisher.publish(StringUtils.defaultString(redisConfig.getExportChannel(), DEFAULT_EXPORT_CHANNEL), message).subscribe();

        return message;
    }
}
