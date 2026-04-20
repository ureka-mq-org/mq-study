package com.ureka.common.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.ureka.common.config.RabbitmqConfig;
import com.ureka.core.event.StudyMessageEvent;

@Component
public class StudyMessagePublisher {

    private final Logger log = LoggerFactory.getLogger(StudyMessagePublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public StudyMessagePublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(StudyMessageEvent event) {
        // 라우팅 키 동적 생성 (예: chat.room.123)
        String routingKey = "chat.room." + event.roomId();

        log.info("Publishing message to room [{}]: {}", event.roomId(), event.content());

        rabbitTemplate.convertAndSend(
                RabbitmqConfig.CHAT_EXCHANGE,
                routingKey,
                event
        );
    }
}
