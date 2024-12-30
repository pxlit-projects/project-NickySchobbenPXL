package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentRequestTests {

    @Test
    void testCommentRequestConstructorAndGetters() {
        String expectedDescription = "This is a comment.";
        String expectedCommenter = "John Doe";

        CommentRequest commentRequest = new CommentRequest(expectedDescription, expectedCommenter);

        assertEquals(expectedDescription, commentRequest.getDescription());
        assertEquals(expectedCommenter, commentRequest.getCommenter());
    }

    @Test
    void testLombokBuilder() {
        String expectedDescription = "This is a comment.";
        String expectedCommenter = "John Doe";

        CommentRequest commentRequest = CommentRequest.builder()
                .description(expectedDescription)
                .commenter(expectedCommenter)
                .build();

        assertNotNull(commentRequest);
        assertEquals(expectedDescription, commentRequest.getDescription());
        assertEquals(expectedCommenter, commentRequest.getCommenter());
    }

    @Test
    void testDefaultConstructorAndSetter() {
        CommentRequest commentRequest = new CommentRequest();

        commentRequest.setDescription("This is a comment.");
        commentRequest.setCommenter("John Doe");

        assertEquals("This is a comment.", commentRequest.getDescription());
        assertEquals("John Doe", commentRequest.getCommenter());
    }

    @Test
    void testEmptyCommentRequest() {
        CommentRequest commentRequest = new CommentRequest();

        assertNull(commentRequest.getDescription());
        assertNull(commentRequest.getCommenter());
    }
}
