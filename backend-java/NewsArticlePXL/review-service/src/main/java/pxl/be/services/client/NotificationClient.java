package pxl.be.services.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pxl.be.services.domain.dto.Notification;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/notification")
    void sendNotification(@RequestBody Notification notification);
}
