package pxl.be.services.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pxl.be.services.client.PostClient;
import pxl.be.services.domain.Review;
import pxl.be.services.domain.ReviewStatus;
import pxl.be.services.domain.dto.Notification;
import pxl.be.services.domain.dto.ReviewRequest;
import pxl.be.services.repository.ReviewRepository;
import pxl.be.services.service.impl.MessagingService;
import pxl.be.services.service.impl.ReviewService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTests {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PostClient postClient;

    @Mock
    private MessagingService messagingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReviewForPost() {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPostId(1L);
        reviewRequest.setReviewerName("John Doe");
        reviewRequest.setReviewContent("Great post!");
        reviewRequest.setReviewStatus(ReviewStatus.APPROVED);

        Review savedReview = Review.builder()
                .id(1L)
                .postId(reviewRequest.getPostId())
                .reviewerName(reviewRequest.getReviewerName())
                .reviewContent(reviewRequest.getReviewContent())
                .reviewStatus(reviewRequest.getReviewStatus())
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);
        doNothing().when(postClient).updatePostStatus(eq(1L), any());
        doNothing().when(messagingService).sendNotification(any(Notification.class));

        Long reviewId = reviewService.addReviewForPost(reviewRequest);

        assertNotNull(reviewId, "The returned review ID should not be null.");
        assertEquals(1L, reviewId, "The returned review ID should match the mocked ID.");
        verify(reviewRepository).save(any(Review.class));
        verify(postClient).updatePostStatus(eq(1L), any());
        verify(messagingService).sendNotification(any(Notification.class));
    }

    @Test
    void testDeleteAllReviewsForPostByPostId() {
        Long postId = 1L;
        Review review = Review.builder()
                .id(1L)
                .postId(postId)
                .reviewerName("John Doe")
                .reviewContent("Great post!")
                .reviewStatus(ReviewStatus.APPROVED)
                .build();

        when(reviewRepository.findAll()).thenReturn(Collections.singletonList(review));
        doNothing().when(reviewRepository).deleteAll(any());

        reviewService.deleteAllReviewsForPostByPostId(postId);

        verify(reviewRepository).findAll();
        verify(reviewRepository).deleteAll(eq(Collections.singletonList(review)));
    }

    @Test
    void testUpdatePostStatus_Approved() {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPostId(1L);
        reviewRequest.setReviewStatus(ReviewStatus.APPROVED);

        Review savedReview = Review.builder()
                .id(1L)
                .postId(reviewRequest.getPostId())
                .reviewStatus(reviewRequest.getReviewStatus())
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);
        doNothing().when(postClient).updatePostStatus(eq(1L), any());
        doNothing().when(messagingService).sendNotification(any(Notification.class));

        reviewService.addReviewForPost(reviewRequest);

        verify(postClient).updatePostStatus(eq(1L), any());
    }

    @Test
    void testUpdatePostStatus_Denied() {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPostId(1L);
        reviewRequest.setReviewStatus(ReviewStatus.DENIED);

        Review savedReview = Review.builder()
                .id(1L)
                .postId(reviewRequest.getPostId())
                .reviewStatus(reviewRequest.getReviewStatus())
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);
        doNothing().when(postClient).updatePostStatus(eq(1L), any());
        doNothing().when(messagingService).sendNotification(any(Notification.class));

        reviewService.addReviewForPost(reviewRequest);

        verify(postClient).updatePostStatus(eq(1L), any());
    }

    @Test
    void testSendNotification_Approved() {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPostId(1L);
        reviewRequest.setReviewerName("John Doe");
        reviewRequest.setReviewContent("Great post!");
        reviewRequest.setReviewStatus(ReviewStatus.APPROVED);

        Review savedReview = Review.builder()
                .id(1L)
                .postId(reviewRequest.getPostId())
                .reviewStatus(reviewRequest.getReviewStatus())
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);
        doNothing().when(postClient).updatePostStatus(eq(1L), any());
        doNothing().when(messagingService).sendNotification(any(Notification.class));

        reviewService.addReviewForPost(reviewRequest);

        verify(messagingService).sendNotification(any(Notification.class));
    }

    @Test
    void testSendNotification_Denied() {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setPostId(1L);
        reviewRequest.setReviewerName("John Doe");
        reviewRequest.setReviewContent("Needs improvement");
        reviewRequest.setReviewStatus(ReviewStatus.DENIED);

        Review savedReview = Review.builder()
                .id(1L)
                .postId(reviewRequest.getPostId())
                .reviewStatus(reviewRequest.getReviewStatus())
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);
        doNothing().when(postClient).updatePostStatus(eq(1L), any());
        doNothing().when(messagingService).sendNotification(any(Notification.class));

        reviewService.addReviewForPost(reviewRequest);

        verify(messagingService).sendNotification(any(Notification.class));
    }

}
