package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;
import pxl.be.services.domain.PostCategory;
import pxl.be.services.domain.PostStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PostResponseTests {

    @Test
    void testConstructorAndGetters() {
        PostResponse postResponse = new PostResponse(
                1L,
                "Test Title",
                "Test Content",
                "Test Author",
                LocalDate.now(),
                PostStatus.PUBLISHED,
                PostCategory.SPORTS
        );

        assertEquals(1L, postResponse.getId());
        assertEquals("Test Title", postResponse.getTitle());
        assertEquals("Test Content", postResponse.getContent());
        assertEquals("Test Author", postResponse.getAuthor());
        assertEquals(LocalDate.now(), postResponse.getDate());
        assertEquals(PostStatus.PUBLISHED, postResponse.getPostStatus());
        assertEquals(PostCategory.SPORTS, postResponse.getCategory());
    }

    @Test
    void testBuilderPattern() {
        PostResponse postResponse = PostResponse.builder()
                .Id(2L)
                .title("Builder Title")
                .content("Builder Content")
                .author("Builder Author")
                .date(LocalDate.now())
                .postStatus(PostStatus.APPROVED)
                .category(PostCategory.SCIENCE)
                .build();

        assertEquals(2L, postResponse.getId());
        assertEquals("Builder Title", postResponse.getTitle());
        assertEquals("Builder Content", postResponse.getContent());
        assertEquals("Builder Author", postResponse.getAuthor());
        assertEquals(LocalDate.now(), postResponse.getDate());
        assertEquals(PostStatus.APPROVED, postResponse.getPostStatus());
        assertEquals(PostCategory.SCIENCE, postResponse.getCategory());
    }

    @Test
    void testNoArgsConstructor() {
        PostResponse postResponse = new PostResponse();

        assertNull(postResponse.getId());
        assertNull(postResponse.getTitle());
        assertNull(postResponse.getContent());
        assertNull(postResponse.getAuthor());
        assertNull(postResponse.getDate());
        assertNull(postResponse.getPostStatus());
        assertNull(postResponse.getCategory());
    }

    @Test
    void testAllArgsConstructor() {
        PostResponse postResponse = new PostResponse(
                3L,
                "All Args Title",
                "All Args Content",
                "All Args Author",
                LocalDate.now(),
                PostStatus.CONCEPT,
                PostCategory.REGIONAL
        );

        assertEquals(3L, postResponse.getId());
        assertEquals("All Args Title", postResponse.getTitle());
        assertEquals("All Args Content", postResponse.getContent());
        assertEquals("All Args Author", postResponse.getAuthor());
        assertEquals(LocalDate.now(), postResponse.getDate());
        assertEquals(PostStatus.CONCEPT, postResponse.getPostStatus());
        assertEquals(PostCategory.REGIONAL, postResponse.getCategory());
    }

    @Test
    void testSetters() {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(4L);
        postResponse.setTitle("Updated Title");
        postResponse.setContent("Updated Content");
        postResponse.setAuthor("Updated Author");
        postResponse.setDate(LocalDate.now());
        postResponse.setPostStatus(PostStatus.WAITING_FOR_APPROVAL);
        postResponse.setCategory(PostCategory.POLITICS);

        assertEquals(4L, postResponse.getId());
        assertEquals("Updated Title", postResponse.getTitle());
        assertEquals("Updated Content", postResponse.getContent());
        assertEquals("Updated Author", postResponse.getAuthor());
        assertEquals(LocalDate.now(), postResponse.getDate());
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, postResponse.getPostStatus());
        assertEquals(PostCategory.POLITICS, postResponse.getCategory());
    }
}
