package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;
import pxl.be.services.domain.PostCategory;
import pxl.be.services.domain.PostStatus;

import static org.junit.jupiter.api.Assertions.*;

public class PostRequestTests {

    @Test
    void testConstructorAndGetters() {
        PostRequest postRequest = new PostRequest(
                "Test Title",
                "Test Content",
                "Test Author",
                PostStatus.PUBLISHED,
                PostCategory.SPORTS
        );

        assertEquals("Test Title", postRequest.getTitle());
        assertEquals("Test Content", postRequest.getContent());
        assertEquals("Test Author", postRequest.getAuthor());
        assertEquals(PostStatus.PUBLISHED, postRequest.getPostStatus());
        assertEquals(PostCategory.SPORTS, postRequest.getCategory());
    }

    @Test
    void testBuilderPattern() {
        PostRequest postRequest = PostRequest.builder()
                .title("Builder Title")
                .content("Builder Content")
                .author("Builder Author")
                .postStatus(PostStatus.APPROVED)
                .category(PostCategory.SCIENCE)
                .build();

        assertEquals("Builder Title", postRequest.getTitle());
        assertEquals("Builder Content", postRequest.getContent());
        assertEquals("Builder Author", postRequest.getAuthor());
        assertEquals(PostStatus.APPROVED, postRequest.getPostStatus());
        assertEquals(PostCategory.SCIENCE, postRequest.getCategory());
    }

    @Test
    void testNoArgsConstructor() {
        PostRequest postRequest = new PostRequest();
        assertNull(postRequest.getTitle());
        assertNull(postRequest.getContent());
        assertNull(postRequest.getAuthor());
        assertNull(postRequest.getPostStatus());
        assertNull(postRequest.getCategory());
    }

    @Test
    void testAllArgsConstructor() {
        PostRequest postRequest = new PostRequest(
                "All Args Title",
                "All Args Content",
                "All Args Author",
                PostStatus.CONCEPT,
                PostCategory.REGIONAL
        );

        assertEquals("All Args Title", postRequest.getTitle());
        assertEquals("All Args Content", postRequest.getContent());
        assertEquals("All Args Author", postRequest.getAuthor());
        assertEquals(PostStatus.CONCEPT, postRequest.getPostStatus());
        assertEquals(PostCategory.REGIONAL, postRequest.getCategory());
    }

    @Test
    void testSetters() {
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("Updated Title");
        postRequest.setContent("Updated Content");
        postRequest.setAuthor("Updated Author");
        postRequest.setPostStatus(PostStatus.WAITING_FOR_APPROVAL);
        postRequest.setCategory(PostCategory.POLITICS);

        assertEquals("Updated Title", postRequest.getTitle());
        assertEquals("Updated Content", postRequest.getContent());
        assertEquals("Updated Author", postRequest.getAuthor());
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, postRequest.getPostStatus());
        assertEquals(PostCategory.POLITICS, postRequest.getCategory());
    }
}
