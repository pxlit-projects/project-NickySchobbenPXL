package pxl.be.services.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pxl.be.services.domain.Comment;
import pxl.be.services.domain.dto.CommentRequest;
import pxl.be.services.domain.dto.CommentResponse;
import pxl.be.services.domain.dto.UpdatableCommentRequest;
import pxl.be.services.exception.CommentNotFoundException;
import pxl.be.services.repository.CommentRepository;
import pxl.be.services.service.impl.CommentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTests {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private CommentRequest commentRequest;
    private CommentResponse commentResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        comment = Comment.builder()
                .id(1L)
                .postId(1L)
                .description("This is a test comment.")
                .commenter("John Doe")
                .dateOfComment(LocalDate.now())
                .build();

        commentRequest = new CommentRequest("This is a test comment.", "John Doe");

        commentResponse = CommentResponse.builder()
                .commentId(1L)
                .postId(1L)
                .commenter("John Doe")
                .description("This is a test comment.")
                .dateOfComment(LocalDate.now())
                .build();
    }

    @Test
    void testUpdateComment() {
        UpdatableCommentRequest updateRequest = new UpdatableCommentRequest("Updated comment description.");
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentResponse result = commentService.updateComment(1L, updateRequest);

        assertNotNull(result);
        assertEquals("Updated comment description.", result.getDescription());
    }

    @Test
    void testDeleteComment() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        doNothing().when(commentRepository).delete(any(Comment.class));

        commentService.deleteComment(1L);

        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void testGetAllCommentsForPost() {
        when(commentRepository.findAll()).thenReturn(List.of(comment));

        List<CommentResponse> result = commentService.getAllCommentsForPost(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(comment.getPostId(), result.get(0).getPostId());
    }

    @Test
    void testGetCommentByIdNotFound() {
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> commentService.getCommentById(1L));
    }
}
