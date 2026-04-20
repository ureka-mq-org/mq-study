package com.ureka.common.config;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String CHAT_EXCHANGE = "chat.topic.exchange";
    public static final String CHAT_QUEUE = "chat.queue";
    public static final String CHAT_ROUTING_KEY = "chat.room.*";

    // DLQ 설정
    public static final String CHAT_DLQ_EXCHANGE = "chat.dlq.exchange";
    public static final String CHAT_DLQ_QUEUE = "chat.dlq.queue";
    public static final String CHAT_DLQ_ROUTING_KEY = "chat.dlq.routing.key";

    @Bean
    public TopicExchange chatExchange() {
        return new TopicExchange(CHAT_EXCHANGE);
    }

    @Bean
    public Queue chatQueue() {
        return QueueBuilder.durable(CHAT_QUEUE)
                .withArgument("x-dead-letter-exchange", CHAT_DLQ_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", CHAT_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding chatBinding(Queue chatQueue, TopicExchange chatExchange) {
        return BindingBuilder.bind(chatQueue).to(chatExchange).with(CHAT_ROUTING_KEY);
    }

    // --- DLQ Beans ---
    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(CHAT_DLQ_EXCHANGE);
    }

    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable(CHAT_DLQ_QUEUE).build();
    }

    @Bean
    public Binding dlqBinding(Queue dlqQueue, DirectExchange dlqExchange) {
        return BindingBuilder.bind(dlqQueue).to(dlqExchange).with(CHAT_DLQ_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(ObjectProvider<JsonMapper> jsonMapperProvider) {
        JsonMapper jsonMapper = jsonMapperProvider.getIfAvailable(() -> JsonMapper.builder().build());
        return new JacksonJsonMessageConverter(jsonMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ObjectProvider<ConnectionFactory> connectionFactoryProvider,
            MessageConverter messageConverter) {

        // 애플리케이션 시작 시 ConnectionFactory가 없는 경우를 방지
        ConnectionFactory connectionFactory = connectionFactoryProvider.getIfAvailable();

        if (connectionFactory == null) {
            throw new IllegalStateException("RabbitMQ ConnectionFactory를 찾을 수 없습니다.");
        }

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
