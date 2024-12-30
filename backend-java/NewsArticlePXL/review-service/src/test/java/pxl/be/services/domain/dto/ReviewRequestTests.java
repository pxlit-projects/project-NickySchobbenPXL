package pxl.be.services.domain.dto;

import org.junit.jupiter.api.Test;
import pxl.be.services.domain.ReviewStatus;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRequestTests {

    @Test
    void testReviewRequestConstructor() {
        ReviewRequest request = new ReviewRequest(1L, "John Doe", "Great post!", ReviewStatus.APPROVED);

        assertEquals(1L, request.getPostId());
        assertEquals("John Doe", request.getReviewerName());
        assertEquals("Great post!", request.getReviewContent());
        assertEquals(ReviewStatus.APPROVED, request.getReviewStatus());
    }

    @Test
    void testReviewRequestBuilder() {
        ReviewRequest request = ReviewRequest.builder()
                .postId(1L)
                .reviewerName("Jane Doe")
                .reviewContent("Excellent article!")
                .reviewStatus(ReviewStatus.DENIED)
                .build();

        assertEquals(1L, request.getPostId());
        assertEquals("Jane Doe", request.getReviewerName());
        assertEquals("Excellent article!", request.getReviewContent());
        assertEquals(ReviewStatus.DENIED, request.getReviewStatus());
    }

    @Test
    void testReviewRequestSetters() {
        ReviewRequest request = new ReviewRequest();

        request.setPostId(2L);
        request.setReviewerName("Sam Smith");
        request.setReviewContent("Good post!");
        request.setReviewStatus(ReviewStatus.APPROVED);

        assertEquals(2L, request.getPostId());
        assertEquals("Sam Smith", request.getReviewerName());
        assertEquals("Good post!", request.getReviewContent());
        assertEquals(ReviewStatus.APPROVED, request.getReviewStatus());
    }

    @Test
    void testReviewRequestDefaultConstructor() {
        ReviewRequest request = new ReviewRequest();

        assertNull(request.getPostId());
        assertNull(request.getReviewerName());
        assertNull(request.getReviewContent());
        assertNull(request.getReviewStatus());
    }
}
