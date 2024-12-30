package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;
import pxl.be.services.domain.PostStatus;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePostStatusRequestTests {

    @Test
    void testConstructorAndGetter() {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest(PostStatus.PUBLISHED);
        assertEquals(PostStatus.PUBLISHED, updatePostStatusRequest.getPostStatus());
    }

    @Test
    void testBuilderPattern() {
        UpdatePostStatusRequest updatePostStatusRequest = UpdatePostStatusRequest.builder()
                .postStatus(PostStatus.APPROVED)
                .build();

        assertEquals(PostStatus.APPROVED, updatePostStatusRequest.getPostStatus());
    }

    @Test
    void testNoArgsConstructor() {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest();
        assertNull(updatePostStatusRequest.getPostStatus());
    }

    @Test
    void testAllArgsConstructor() {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest(PostStatus.WAITING_FOR_APPROVAL);
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, updatePostStatusRequest.getPostStatus());
    }

    @Test
    void testSetter() {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest();
        updatePostStatusRequest.setPostStatus(PostStatus.CONCEPT);
        assertEquals(PostStatus.CONCEPT, updatePostStatusRequest.getPostStatus());
    }
}
