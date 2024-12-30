package pxl.be.services.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pxl.be.services.domain.dto.CommentRequest;
import pxl.be.services.domain.dto.CommentResponse;
import pxl.be.services.domain.dto.UpdatableCommentRequest;
import pxl.be.services.service.ICommentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentControllerTests {

    @Mock
    private ICommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private CommentResponse commentResponse;
    private CommentRequest commentRequest;
    private UpdatableCommentRequest updatableCommentRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        commentResponse = CommentResponse.builder()
                .commentId(1L)
                .postId(1L)
                .commenter("John Doe")
                .description("This is a test comment.")
                .dateOfComment(java.time.LocalDate.now())
                .build();

        commentRequest = new CommentRequest("This is a test comment.", "John Doe");

        updatableCommentRequest = new UpdatableCommentRequest("Updated comment description.");
    }

    @Test
    void testGetAllCommentsByPostId() {
        List<CommentResponse> comments = List.of(commentResponse);
        when(commentService.getAllCommentsForPost(1L)).thenReturn(comments);

        ResponseEntity<List<CommentResponse>> result = commentController.getAllCommentsByPostId(1L);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(comments, result.getBody());
    }

    @Test
    void testAddNewComment() {
        when(commentService.createNewComment(1L, commentRequest)).thenReturn(commentResponse);

        ResponseEntity<CommentResponse> result = commentController.addNewComment(1L, commentRequest);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(commentResponse, result.getBody());
    }

    @Test
    void testUpdateComment() {
        when(commentService.updateComment(1L, updatableCommentRequest)).thenReturn(commentResponse);

        ResponseEntity<CommentResponse> result = commentController.updateComment(1L, updatableCommentRequest);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(commentResponse, result.getBody());
    }

    @Test
    void testDeleteComment() {
        doNothing().when(commentService).deleteComment(1L);

        ResponseEntity<Void> result = commentController.deleteComment(1L);

        assertNotNull(result);
        assertEquals(204, result.getStatusCodeValue());
        verify(commentService, times(1)).deleteComment(1L);
    }
}
