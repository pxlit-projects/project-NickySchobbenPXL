package pxl.be.services.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pxl.be.services.domain.Notification;
import pxl.be.services.service.impl.NotificationService;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody Notification notification) {
        notificationService.sendMessage(notification);
    }
}
