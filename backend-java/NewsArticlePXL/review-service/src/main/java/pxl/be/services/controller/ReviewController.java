package pxl.be.services.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxl.be.services.domain.dto.ReviewRequest;
import pxl.be.services.services.ReviewService;

import java.net.URI;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Long> addReviewToPost(@RequestBody ReviewRequest reviewRequest) {
        LOGGER.info("Attempting to add review for post..");
        Long id = reviewService.addReviewForPost(reviewRequest);
        return ResponseEntity.created(URI.create("/api/review/" + id)).build();
    }
}
