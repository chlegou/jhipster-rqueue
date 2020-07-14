package com.chlegou.demos.rqueue.config;

import com.github.sonus21.rqueue.config.SimpleRqueueListenerContainerFactory;
import com.github.sonus21.rqueue.listener.RqueueMessageHandler;
import com.github.sonus21.rqueue.models.enums.PriorityMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Objects;


@EnableRedisRepositories
@Configuration
public class RQueueConfiguration {

    private final AsyncConfiguration asyncConfiguration;

    public RQueueConfiguration(AsyncConfiguration asyncConfiguration) {
        this.asyncConfiguration = asyncConfiguration;
    }

    @Bean
    public SimpleRqueueListenerContainerFactory simpleRqueueListenerContainerFactory(RqueueMessageHandler rqueueMessageHandler) {
        SimpleRqueueListenerContainerFactory factory = new SimpleRqueueListenerContainerFactory();

        // Stand alone redis configuration, Set fields of redis configuration
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();


        // Create lettuce connection factory
        LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(redisConfiguration);
        redisConnectionFactory.afterPropertiesSet();
        factory.setRedisConnectionFactory(redisConnectionFactory);
        factory.setPriorityMode(PriorityMode.STRICT);

        factory.setTaskExecutor((AsyncTaskExecutor) Objects.requireNonNull(asyncConfiguration.getAsyncExecutor()));

        return factory;
    }



}
