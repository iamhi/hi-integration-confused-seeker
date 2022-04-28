package com.github.iamhi.hiintegration.confusedseeker.service.out;

import com.github.iamhi.hiintegration.confusedseeker.service.config.RedisConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class RedisClientWrapper implements RedisPublisher {
    RedisReactiveCommands<String, String> reactiveCommands;

    RedisPubSubReactiveCommands<String, String> reactiveSubCommands;

    RedisPubSubReactiveCommands<String, String> reactivePubCommands;

    public RedisClientWrapper(RedisConfig redisConfig) {
        RedisClient redisClient = RedisClient.create("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort());
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        reactiveSubCommands = redisClient.connectPubSub().reactive();
        reactivePubCommands = redisClient.connectPubSub().reactive();

        reactiveCommands = connection.reactive();
    }

    @Override
    public Mono<Long> publish(String channel, String message) {
        return reactivePubCommands.publish(channel, message);
    }
}

