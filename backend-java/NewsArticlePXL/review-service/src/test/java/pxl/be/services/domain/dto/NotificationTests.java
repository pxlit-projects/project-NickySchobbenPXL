package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTests {

    @Test
    void testNotificationConstructor() {
        Notification notification = new Notification("System", "Your comment has been approved!");

        assertEquals("System", notification.getSender());
        assertEquals("Your comment has been approved!", notification.getMessage());
    }

    @Test
    void testNotificationBuilder() {
        Notification notification = Notification.builder()
                .sender("Admin")
                .message("Your post is under review.")
                .build();

        assertEquals("Admin", notification.getSender());
        assertEquals("Your post is under review.", notification.getMessage());
    }

    @Test
    void testNotificationSetters() {
        Notification notification = new Notification();

        notification.setSender("Moderator");
        notification.setMessage("Your comment needs moderation.");

        assertEquals("Moderator", notification.getSender());
        assertEquals("Your comment needs moderation.", notification.getMessage());
    }

    @Test
    void testNotificationDefaultConstructor() {
        Notification notification = new Notification();

        assertNull(notification.getSender());
        assertNull(notification.getMessage());
    }
}
