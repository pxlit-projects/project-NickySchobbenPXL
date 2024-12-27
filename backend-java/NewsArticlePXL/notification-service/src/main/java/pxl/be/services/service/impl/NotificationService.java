package pxl.be.services.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.Notification;
import pxl.be.services.service.INotificationService;


@Service
public class NotificationService implements INotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    @Override
    public void sendMessage(Notification notification) {
        LOGGER.info("Sender: " + notification.getSender());
        LOGGER.info("Message: " + "\n" + notification.getMessage());
    }
}
