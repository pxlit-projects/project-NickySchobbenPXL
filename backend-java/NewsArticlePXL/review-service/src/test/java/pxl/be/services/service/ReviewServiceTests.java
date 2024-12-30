package pxl.be.services.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pxl.be.services.client.NotificationClient;
import pxl.be.services.client.PostClient;
import pxl.be.services.domain.Review;
import pxl.be.services.domain.ReviewStatus;
import pxl.be.services.domain.dto.Notification;
import pxl.be.services.domain.dto.ReviewRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;
import pxl.be.services.repository.ReviewRepository;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTests {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PostClient postClient;

    @Mock
    private NotificationClient notificationClient;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void testAddReviewForPost_Approved() {
        ReviewRequest reviewRequest = ReviewRequest.builder()
                .postId(1L)
                .reviewerName("John Doe")
                .reviewContent("Great post!")
                .reviewStatus(ReviewStatus.APPROVED)
                .build();

        Review mockReview = Review.builder()
                .id(1L)
                .postId(1L)
                .reviewerName("John Doe")
                .reviewContent("Great post!")
                .reviewStatus(ReviewStatus.APPROVED)
                .reviewDate(LocalDate.now())
                .build();

        when(reviewRepository.save(any())).thenReturn(mockReview);
        doNothing().when(postClient).updatePostStatus(any(), any());
        doNothing().when(notificationClient).sendNotification(any());

        Long reviewId = reviewService.addReviewForPost(reviewRequest);

        assertNotNull(reviewId);
        verify(postClient, times(1)).updatePostStatus(eq(1L), any(UpdatePostStatusRequest.class));
        verify(notificationClient, times(1)).sendNotification(any(Notification.class));
    }


    @Test
    void testAddReviewForPost_Denied() {
        ReviewRequest reviewRequest = ReviewRequest.builder()
                .postId(2L)
                .reviewerName("Jane Doe")
                .reviewContent("Needs improvement.")
                .reviewStatus(ReviewStatus.DENIED)
                .build();

        Review mockReview = Review.builder()
                .id(1L)
                .postId(2L)
                .reviewerName("Jane Doe")
                .reviewContent("Needs improvement.")
                .reviewStatus(ReviewStatus.DENIED)
                .reviewDate(LocalDate.now())
                .build();

        when(reviewRepository.save(any())).thenReturn(mockReview);
        doNothing().when(postClient).updatePostStatus(any(), any());
        doNothing().when(notificationClient).sendNotification(any());

        Long reviewId = reviewService.addReviewForPost(reviewRequest);

        // Then
        assertNotNull(reviewId);
        verify(postClient, times(1)).updatePostStatus(eq(2L), any(UpdatePostStatusRequest.class));
        verify(notificationClient, times(1)).sendNotification(any(Notification.class));
    }


    @Test
    void testMapReviewRequestToReviewEntity() {
        ReviewRequest reviewRequest = ReviewRequest.builder()
                .postId(3L)
                .reviewerName("Admin")
                .reviewContent("Good content, needs minor revisions.")
                .reviewStatus(ReviewStatus.APPROVED)
                .build();

        Review review = reviewService.mapReviewRequestToReviewEntity(reviewRequest);

        // Then
        assertEquals(reviewRequest.getPostId(), review.getPostId());
        assertEquals(reviewRequest.getReviewerName(), review.getReviewerName());
        assertEquals(reviewRequest.getReviewContent(), review.getReviewContent());
        assertEquals(reviewRequest.getReviewStatus(), review.getReviewStatus());
    }

    @Test
    void testSendNotification() {
        // Given
        ReviewRequest reviewRequest = ReviewRequest.builder()
                .postId(4L)
                .reviewerName("Moderator")
                .reviewContent("Please address the following issues.")
                .reviewStatus(ReviewStatus.DENIED)
                .build();

        // When
        reviewService.sendNotification(reviewRequest);

        // Then
        verify(notificationClient, times(1)).sendNotification(any(Notification.class));
    }
}
