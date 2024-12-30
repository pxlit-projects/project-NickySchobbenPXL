package pxl.be.services.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pxl.be.services.domain.ReviewStatus;
import pxl.be.services.domain.dto.ReviewRequest;
import pxl.be.services.service.ReviewService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void testAddReviewToPost_Approved() throws Exception {
        when(reviewService.addReviewForPost(any(ReviewRequest.class))).thenReturn(1L);

        mockMvc.perform(post("/api/review")
                        .contentType("application/json")
                        .content("{\"postId\": 1, \"reviewerName\": \"John Doe\", \"reviewContent\": \"Great post!\", \"reviewStatus\": \"APPROVED\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/review/1"));

        verify(reviewService, times(1)).addReviewForPost(any(ReviewRequest.class));
    }

    @Test
    void testAddReviewToPost_Denied() throws Exception {
        when(reviewService.addReviewForPost(any(ReviewRequest.class))).thenReturn(2L);

        mockMvc.perform(post("/api/review")
                        .contentType("application/json")
                        .content("{\"postId\": 2, \"reviewerName\": \"Jane Doe\", \"reviewContent\": \"Needs improvement.\", \"reviewStatus\": \"DENIED\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/review/2"));

        verify(reviewService, times(1)).addReviewForPost(any(ReviewRequest.class));
    }
}
