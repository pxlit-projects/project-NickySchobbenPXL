package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;
import pxl.be.services.domain.PostStatus;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePostStatusRequestTests {

    @Test
    void testUpdatePostStatusRequestConstructor() {
        UpdatePostStatusRequest request = new UpdatePostStatusRequest(PostStatus.APPROVED);

        assertEquals(PostStatus.APPROVED, request.getPostStatus());
    }

    @Test
    void testUpdatePostStatusRequestBuilder() {
        UpdatePostStatusRequest request = UpdatePostStatusRequest.builder()
                .postStatus(PostStatus.WAITING_FOR_APPROVAL)
                .build();

        assertEquals(PostStatus.WAITING_FOR_APPROVAL, request.getPostStatus());
    }

    @Test
    void testUpdatePostStatusRequestSetters() {
        UpdatePostStatusRequest request = new UpdatePostStatusRequest();

        request.setPostStatus(PostStatus.APPROVED);

        assertEquals(PostStatus.APPROVED, request.getPostStatus());
    }

    @Test
    void testUpdatePostStatusRequestDefaultConstructor() {
        UpdatePostStatusRequest request = new UpdatePostStatusRequest();

        assertNull(request.getPostStatus());
    }
}
