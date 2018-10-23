package com.ddl.learn.eventbus.config;

import com.google.common.collect.Lists;
import com.hk.cargo.infrastructure.eventbus.EventPublisher;
import com.hk.cargo.infrastructure.eventbus.components.ChannelEventPublisher;
import com.hk.cargo.infrastructure.eventbus.components.EventBusBuilderImpl;
import com.hk.cargo.infrastructure.eventbus.proxy.AnnotationSubscriberConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableIntegration
@Import({AnnotationSubscriberConfig.class, EventBusTransactionConfig.class})
public class EventBusConfig {

    @Autowired(required = false)
    private List<EventBusConfigurer> eventBusConfigurers = Collections.emptyList();


    @Bean
    public MessageChannel eventBusInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public EventPublisher eventPublisher() {
        return new ChannelEventPublisher(eventBusInputChannel());
    }


    @Bean
    public IntegrationFlow eventBus() {
        EventBusBuilderImpl builder = new EventBusBuilderImpl(eventBusInputChannel());

        // reverse the list so the configurer with the highest precedence gets called last
        Lists.reverse(eventBusConfigurers)
                .forEach(c -> c.configureEventBus(builder));

        return builder.build();
    }
}
