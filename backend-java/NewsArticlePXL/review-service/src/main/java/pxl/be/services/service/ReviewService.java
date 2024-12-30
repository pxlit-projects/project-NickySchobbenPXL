package pxl.be.services.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pxl.be.services.client.NotificationClient;
import pxl.be.services.client.PostClient;
import pxl.be.services.domain.PostStatus;
import pxl.be.services.domain.Review;
import pxl.be.services.domain.ReviewStatus;
import pxl.be.services.domain.dto.Notification;
import pxl.be.services.domain.dto.ReviewRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;
import pxl.be.services.repository.ReviewRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final PostClient postClient;
    private final NotificationClient notificationClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);
    @Override
    public Long addReviewForPost(ReviewRequest reviewRequest) {
        LOGGER.info("Adding review for post with postId: " + reviewRequest.getPostId() + "\n");
        Review newReview = mapReviewRequestToReviewEntity(reviewRequest);
        Long id = reviewRepository.save(newReview).getId();
        LOGGER.info("Succesfully added the review!" + "\n");
        updatePostStatus(reviewRequest);
        sendNotification(reviewRequest);
        return id;
    }

    private void updatePostStatus(ReviewRequest reviewRequest) {
        LOGGER.info("Attempting to update the poststatus for post with id: " + reviewRequest.getPostId() + "\n");
        PostStatus postStatus = PostStatus.APPROVED;
        if (reviewRequest.getReviewStatus() == ReviewStatus.DENIED) {
            postStatus = PostStatus.WAITING_FOR_APPROVAL;
        }
        UpdatePostStatusRequest updatePostStatusRequest = UpdatePostStatusRequest.builder()
                .postStatus(postStatus)
                .build();

        postClient.updatePostStatus(reviewRequest.getPostId(), updatePostStatusRequest);
        LOGGER.info("Request to chat-service to update PostStatus for chat with id " + reviewRequest.getPostId() + " has been sent.");
    }

    void sendNotification(ReviewRequest reviewRequest) {
        LOGGER.info("Sending notification to notification-service.. ");
        StringBuilder sb = new StringBuilder();
        sb.append("Review has been done for post with id: ").append(reviewRequest.getPostId()).append("\n");
        sb.append("Result: ").append(reviewRequest.getReviewStatus()).append("\n");
        if (reviewRequest.getReviewStatus() == ReviewStatus.APPROVED) {
            sb.append("The post can now be published!");
        }
        else {
            sb.append("Reason: ").append(reviewRequest.getReviewContent()).append("\n");
            sb.append("Please make changes based on the given information. Once done, request another review so that we can look into it again.");
        }

        String message = sb.toString();

        Notification notification = Notification.builder()
                .sender(reviewRequest.getReviewerName())
                .message(message)
                .build();

        notificationClient.sendNotification(notification);
    }

    Review mapReviewRequestToReviewEntity(ReviewRequest reviewRequest) {
        return Review.builder()
                .postId(reviewRequest.getPostId())
                .reviewerName(reviewRequest.getReviewerName())
                .reviewContent(reviewRequest.getReviewContent())
                .reviewStatus(reviewRequest.getReviewStatus())
                .reviewDate(LocalDate.now())
                .build();
    }
}
