package pxl.be.services.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pxl.be.services.domain.dto.CommentRequest;
import pxl.be.services.domain.dto.CommentResponse;
import pxl.be.services.domain.dto.UpdatableCommentRequest;
import pxl.be.services.service.CommentService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
class CommentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

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
                .dateOfComment(LocalDate.now())
                .build();

        commentRequest = new CommentRequest("This is a test comment.", "John Doe");
        updatableCommentRequest = new UpdatableCommentRequest("Updated comment description.");
    }

    @Test
    void testGetAllCommentsByPostId() throws Exception {
        List<CommentResponse> comments = List.of(commentResponse);
        when(commentService.getAllCommentsForPost(1L)).thenReturn(comments);

        mockMvc.perform(get("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(comments.size()));

        verify(commentService, times(1)).getAllCommentsForPost(1L);
    }

    @Test
    void testAddNewComment() throws Exception {
        when(commentService.createNewComment(eq(1L), any(CommentRequest.class)))
                .thenReturn(commentResponse);

        String commentRequestString = objectMapper.writeValueAsString(commentRequest);

        mockMvc.perform(post("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentRequestString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commenter").value(commentResponse.getCommenter()))
                .andExpect(jsonPath("$.description").value(commentResponse.getDescription()));

        verify(commentService, times(1)).createNewComment(eq(1L), any(CommentRequest.class));
    }

    @Test
    void testUpdateComment() throws Exception {
        when(commentService.updateComment(eq(1L), any(UpdatableCommentRequest.class))).thenReturn(commentResponse);

        String updatableCommentString = objectMapper.writeValueAsString(updatableCommentRequest);

        mockMvc.perform(put("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatableCommentString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commenter").value(commentResponse.getCommenter()))
                .andExpect(jsonPath("$.description").value(commentResponse.getDescription()));

        verify(commentService, times(1)).updateComment(eq(1L), any(UpdatableCommentRequest.class));
    }


    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(commentService).deleteComment(1L);

        mockMvc.perform(delete("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(commentService, times(1)).deleteComment(1L);
    }

    @Test
    void testDeleteAllCommentsForPost() throws Exception {
        doNothing().when(commentService).deleteAllCommentsForPostByPostId(1L);

        mockMvc.perform(delete("/api/comment/1/delete-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(commentService, times(1)).deleteAllCommentsForPostByPostId(1L);
    }
}