package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;
import pxl.be.services.domain.PostCategory;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatablePostRequestTests {

    @Test
    void testConstructorAndGetters() {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest(
                "Updated Title",
                "Updated Content",
                "Updated Author",
                PostCategory.SCIENCE
        );
        assertEquals("Updated Title", updatablePostRequest.getTitle());
        assertEquals("Updated Content", updatablePostRequest.getContent());
        assertEquals("Updated Author", updatablePostRequest.getAuthor());
        assertEquals(PostCategory.SCIENCE, updatablePostRequest.getCategory());
    }

    @Test
    void testBuilderPattern() {
        UpdatablePostRequest updatablePostRequest = UpdatablePostRequest.builder()
                .title("Builder Title")
                .content("Builder Content")
                .author("Builder Author")
                .category(PostCategory.POLITICS)
                .build();

        assertEquals("Builder Title", updatablePostRequest.getTitle());
        assertEquals("Builder Content", updatablePostRequest.getContent());
        assertEquals("Builder Author", updatablePostRequest.getAuthor());
        assertEquals(PostCategory.POLITICS, updatablePostRequest.getCategory());
    }

    @Test
    void testNoArgsConstructor() {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest();
        assertNull(updatablePostRequest.getTitle());
        assertNull(updatablePostRequest.getContent());
        assertNull(updatablePostRequest.getAuthor());
        assertNull(updatablePostRequest.getCategory());
    }

    @Test
    void testAllArgsConstructor() {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest(
                "All Args Title",
                "All Args Content",
                "All Args Author",
                PostCategory.REGIONAL
        );

        assertEquals("All Args Title", updatablePostRequest.getTitle());
        assertEquals("All Args Content", updatablePostRequest.getContent());
        assertEquals("All Args Author", updatablePostRequest.getAuthor());
        assertEquals(PostCategory.REGIONAL, updatablePostRequest.getCategory());
    }

    @Test
    void testSetters() {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest();
        updatablePostRequest.setTitle("Updated Title");
        updatablePostRequest.setContent("Updated Content");
        updatablePostRequest.setAuthor("Updated Author");
        updatablePostRequest.setCategory(PostCategory.SPORTS);

        assertEquals("Updated Title", updatablePostRequest.getTitle());
        assertEquals("Updated Content", updatablePostRequest.getContent());
        assertEquals("Updated Author", updatablePostRequest.getAuthor());
        assertEquals(PostCategory.SPORTS, updatablePostRequest.getCategory());
    }
}
