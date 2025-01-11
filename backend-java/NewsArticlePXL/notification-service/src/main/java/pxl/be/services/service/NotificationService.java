package pxl.be.services.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.Notification;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    @RabbitListener(queues = "NotificationQueue")
    public void processReviewMessage(Notification notification) {
        LOGGER.info("Retrieved a request to process a notification.");
        LOGGER.info("Sender: " + notification.getSender());
        LOGGER.info("Message: " + "\n" + notification.getMessage());
    }
}
