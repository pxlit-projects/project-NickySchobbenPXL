package pxl.be.services.service;

import pxl.be.services.domain.Notification;

public interface INotificationService {

    void sendMessage(Notification notification);
}
