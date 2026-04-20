package com.ureka.common.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ureka.common.config.RabbitmqConfig;
import com.ureka.core.event.StudyMessageEvent;

@Component
public class StudyMessageListener {

    private final Logger log = LoggerFactory.getLogger(StudyMessageListener.class);

    @RabbitListener(queues = RabbitmqConfig.CHAT_QUEUE)
    public void receiveMessage(StudyMessageEvent event) {
        try {
            log.info("Received message from sender [{}] in room [{}]: {}",
                    event.senderId(), event.roomId(), event.content());

            // TODO: 웹소켓 전송 또는 DB 저장 로직 구현

        } catch (Exception e) {
            log.error("Failed to process message: {}", event.eventId(), e);
            // RuntimeException 발생 시 기본적으로 설정된 Retry 횟수만큼 재시도 후 DLQ로 이동합니다.
            throw new RuntimeException("Message processing failed", e);
        }
    }
}
