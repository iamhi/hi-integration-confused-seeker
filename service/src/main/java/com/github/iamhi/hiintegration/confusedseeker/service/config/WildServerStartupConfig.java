package com.github.iamhi.hiintegration.confusedseeker.service.config;

import com.github.iamhi.hiintegration.confusedseeker.core.MadOrganizer;
import com.github.iamhi.hiintegration.confusedseeker.out.CreepyConnector;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WildServerStartupConfig {

    private final WildServerConnectConfig wildServerConfig;

    private final CreepyConnector creepyConnector;

    private final MadOrganizer madOrganizer;

    @PostConstruct
    private void conenct() {
        madOrganizer.start().then(creepyConnector.joinChannel(wildServerConfig.listen)).subscribe();
    }
}
