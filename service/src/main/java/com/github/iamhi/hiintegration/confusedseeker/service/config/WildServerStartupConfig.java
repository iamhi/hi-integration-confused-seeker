package com.github.iamhi.hiintegration.confusedseeker.service.config;

import com.github.iamhi.hiintegration.confusedseeker.out.CreepyConnector;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WildServerStartupConfig {

    private final WildServerConnectConfig wildServerConfig;

    private final CreepyConnector creepyConnector;

    @PostConstruct
    private void conenct() {
        creepyConnector.joinChannel(wildServerConfig.listen).subscribe();
    }

}
