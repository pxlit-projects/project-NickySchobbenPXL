package pxl.be.services.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CommentTests {

    @Test
    void testCommentConstructorAndGetters() {
        Long expectedId = 1L;
        Long expectedPostId = 10L;
        String expectedDescription = "This is a comment";
        String expectedCommenter = "John Doe";
        LocalDate expectedDateOfComment = LocalDate.now();

        Comment comment = new Comment(expectedId, expectedPostId, expectedDescription, expectedCommenter, expectedDateOfComment);

        assertEquals(expectedId, comment.getId());
        assertEquals(expectedPostId, comment.getPostId());
        assertEquals(expectedDescription, comment.getDescription());
        assertEquals(expectedCommenter, comment.getCommenter());
        assertEquals(expectedDateOfComment, comment.getDateOfComment());
    }

    @Test
    void testLombokBuilder() {
        Comment comment = Comment.builder()
                .id(1L)
                .postId(10L)
                .description("This is a comment")
                .commenter("Jane Doe")
                .dateOfComment(LocalDate.now())
                .build();

        assertNotNull(comment);
        assertEquals(1L, comment.getId());
        assertEquals(10L, comment.getPostId());
        assertEquals("This is a comment", comment.getDescription());
        assertEquals("Jane Doe", comment.getCommenter());
        assertEquals(LocalDate.now(), comment.getDateOfComment());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        Comment comment = new Comment();

        comment.setId(1L);
        comment.setPostId(10L);
        comment.setDescription("This is a comment");
        comment.setCommenter("John Doe");
        comment.setDateOfComment(LocalDate.now());

        assertEquals(1L, comment.getId());
        assertEquals(10L, comment.getPostId());
        assertEquals("This is a comment", comment.getDescription());
        assertEquals("John Doe", comment.getCommenter());
        assertEquals(LocalDate.now(), comment.getDateOfComment());
    }

    @Test
    void testEmptyComment() {
        Comment comment = new Comment();

        assertNull(comment.getId());
        assertNull(comment.getPostId());
        assertNull(comment.getDescription());
        assertNull(comment.getCommenter());
        assertNull(comment.getDateOfComment());
    }
}
