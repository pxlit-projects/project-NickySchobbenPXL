package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CommentResponseTests {

    @Test
    void testCommentResponseConstructorAndGetters() {
        Long expectedCommentId = 1L;
        Long expectedPostId = 100L;
        String expectedDescription = "This is a comment.";
        String expectedCommenter = "John Doe";
        LocalDate expectedDateOfComment = LocalDate.now();

        CommentResponse commentResponse = new CommentResponse(
                expectedCommentId, expectedPostId, expectedDescription, expectedCommenter, expectedDateOfComment
        );

        assertEquals(expectedCommentId, commentResponse.getCommentId());
        assertEquals(expectedPostId, commentResponse.getPostId());
        assertEquals(expectedDescription, commentResponse.getDescription());
        assertEquals(expectedCommenter, commentResponse.getCommenter());
        assertEquals(expectedDateOfComment, commentResponse.getDateOfComment());
    }

    @Test
    void testLombokBuilder() {
        Long expectedCommentId = 1L;
        Long expectedPostId = 100L;
        String expectedDescription = "This is a comment.";
        String expectedCommenter = "John Doe";
        LocalDate expectedDateOfComment = LocalDate.now();

        CommentResponse commentResponse = CommentResponse.builder()
                .commentId(expectedCommentId)
                .postId(expectedPostId)
                .description(expectedDescription)
                .commenter(expectedCommenter)
                .dateOfComment(expectedDateOfComment)
                .build();

        assertNotNull(commentResponse);
        assertEquals(expectedCommentId, commentResponse.getCommentId());
        assertEquals(expectedPostId, commentResponse.getPostId());
        assertEquals(expectedDescription, commentResponse.getDescription());
        assertEquals(expectedCommenter, commentResponse.getCommenter());
        assertEquals(expectedDateOfComment, commentResponse.getDateOfComment());
    }

    @Test
    void testDefaultConstructorAndSetter() {
        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setCommentId(1L);
        commentResponse.setPostId(100L);
        commentResponse.setDescription("This is a comment.");
        commentResponse.setCommenter("John Doe");
        commentResponse.setDateOfComment(LocalDate.now());

        assertEquals(1L, commentResponse.getCommentId());
        assertEquals(100L, commentResponse.getPostId());
        assertEquals("This is a comment.", commentResponse.getDescription());
        assertEquals("John Doe", commentResponse.getCommenter());
        assertNotNull(commentResponse.getDateOfComment());
    }

    @Test
    void testEmptyCommentResponse() {
        CommentResponse commentResponse = new CommentResponse();

        assertNull(commentResponse.getCommentId());
        assertNull(commentResponse.getPostId());
        assertNull(commentResponse.getDescription());
        assertNull(commentResponse.getCommenter());
        assertNull(commentResponse.getDateOfComment());
    }
}
