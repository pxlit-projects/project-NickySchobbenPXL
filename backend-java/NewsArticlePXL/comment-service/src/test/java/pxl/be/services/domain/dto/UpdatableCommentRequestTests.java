package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdatableCommentRequestTests {

    @Test
    void testUpdatableCommentRequestConstructorAndGetters() {
        String expectedDescription = "Updated comment description";

        UpdatableCommentRequest updatableCommentRequest = new UpdatableCommentRequest(expectedDescription);

        assertEquals(expectedDescription, updatableCommentRequest.getDescription());
    }

    @Test
    void testLombokBuilder() {
        UpdatableCommentRequest updatableCommentRequest = UpdatableCommentRequest.builder()
                .description("Updated comment description")
                .build();

        assertNotNull(updatableCommentRequest);
        assertEquals("Updated comment description", updatableCommentRequest.getDescription());
    }

    @Test
    void testDefaultConstructorAndSetter() {
        UpdatableCommentRequest updatableCommentRequest = new UpdatableCommentRequest();

        updatableCommentRequest.setDescription("Updated comment description");

        assertEquals("Updated comment description", updatableCommentRequest.getDescription());
    }

    @Test
    void testEmptyUpdatableCommentRequest() {
        UpdatableCommentRequest updatableCommentRequest = new UpdatableCommentRequest();

        assertNull(updatableCommentRequest.getDescription());
    }

    @Test
    void testUpdateCommentDescription() {
        String newDescription = "This is the updated description.";
        UpdatableCommentRequest request = new UpdatableCommentRequest(newDescription);

        String description = request.getDescription();

        assertEquals(newDescription, description);
    }
}
