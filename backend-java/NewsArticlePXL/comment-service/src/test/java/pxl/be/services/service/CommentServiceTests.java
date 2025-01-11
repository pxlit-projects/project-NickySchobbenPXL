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

    @Test
    void testDeleteAllCommentsForPostByPostId() {
        Long postId = 1L;
        List<Comment> comments = List.of(
                Comment.builder().id(1L).postId(postId).description("First comment").build(),
                Comment.builder().id(2L).postId(postId).description("Second comment").build()
        );

        when(commentRepository.findAll()).thenReturn(comments);
        doNothing().when(commentRepository).deleteAll(anyList());

        commentService.deleteAllCommentsForPostByPostId(postId);
        verify(commentRepository).findAll();
        verify(commentRepository).deleteAll(comments);
    }

    @Test
    void testCreateNewComment() {
        Long postId = 1L;
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommenter("John Doe");
        commentRequest.setDescription("This is a comment.");

        Comment savedComment = Comment.builder()
                .id(1L)
                .postId(postId)
                .commenter(commentRequest.getCommenter())
                .description(commentRequest.getDescription())
                .dateOfComment(LocalDate.now())
                .build();

        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);
        CommentResponse response = commentService.createNewComment(postId, commentRequest);
        assertEquals("John Doe", response.getCommenter());
        assertEquals("This is a comment.", response.getDescription());
        verify(commentRepository).save(any(Comment.class));
    }
}
