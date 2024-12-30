package pxl.be.services.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTests {

    private Review review;

    @BeforeEach
    void setUp() {
        review = Review.builder()
                .id(1L)
                .postId(1L)
                .reviewerName("Jane Doe")
                .reviewContent("Excellent post!")
                .reviewStatus(ReviewStatus.APPROVED)
                .reviewDate(LocalDate.now())
                .build();
    }

    @Test
    void testReviewEntityFields() {
        assertNotNull(review);

        assertEquals(1L, review.getId());
        assertEquals(1L, review.getPostId());
        assertEquals("Jane Doe", review.getReviewerName());
        assertEquals("Excellent post!", review.getReviewContent());
        assertEquals(ReviewStatus.APPROVED, review.getReviewStatus());
        assertEquals(LocalDate.now(), review.getReviewDate());
    }

    @Test
    void testReviewBuilder() {
        Review newReview = Review.builder()
                .id(2L)
                .postId(2L)
                .reviewerName("John Smith")
                .reviewContent("Not bad, but could be better.")
                .reviewStatus(ReviewStatus.APPROVED)
                .reviewDate(LocalDate.of(2024, 12, 30))
                .build();

        assertNotNull(newReview);
        assertEquals(2L, newReview.getId());
        assertEquals(2L, newReview.getPostId());
        assertEquals("John Smith", newReview.getReviewerName());
        assertEquals("Not bad, but could be better.", newReview.getReviewContent());
        assertEquals(ReviewStatus.APPROVED, newReview.getReviewStatus());
        assertEquals(LocalDate.of(2024, 12, 30), newReview.getReviewDate());
    }

    @Test
    void testReviewStatusEnum() {
        ReviewStatus approvedStatus = ReviewStatus.APPROVED;
        ReviewStatus pendingStatus = ReviewStatus.DENIED;

        assertEquals("APPROVED", approvedStatus.name());
        assertEquals("DENIED", pendingStatus.name());

        assertEquals(ReviewStatus.APPROVED, ReviewStatus.valueOf("APPROVED"));
        assertEquals(ReviewStatus.DENIED, ReviewStatus.valueOf("DENIED"));
    }
}
