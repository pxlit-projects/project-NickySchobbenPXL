package pxl.be.services.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.dto.Notification;

@Service
@RequiredArgsConstructor
public class MessagingService {
    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(Notification notification) {
        rabbitTemplate.convertAndSend("NotificationQueue", notification);
    }
}
