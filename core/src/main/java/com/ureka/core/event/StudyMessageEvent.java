package com.ureka.core.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyMessageEvent(
        String eventId,
        String roomId,
        String senderId,
        String content,
        LocalDateTime time
) {
    public static StudyMessageEvent of(String roomId, String senderId, String content){
        return new StudyMessageEvent(
                UUID.randomUUID().toString(),
                roomId,
                senderId,
                content,
                LocalDateTime.now()
        );
    }
}
