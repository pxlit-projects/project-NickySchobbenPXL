package pxl.be.services.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pxl.be.services.domain.dto.*;
import pxl.be.services.service.IPostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTests {

    @MockBean
    private IPostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllUnpublishedPosts() throws Exception {
        PostResponse postResponse = new PostResponse(1L, "Title", "Content", "Author", null, null, null);
        when(postService.getAllUnpublishedPosts()).thenReturn(List.of(postResponse));

        mockMvc.perform(get("/api/posts/unpublished"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(postResponse.getTitle()))
                .andExpect(jsonPath("$[0].content").value(postResponse.getContent()))
                .andExpect(jsonPath("$[0].author").value(postResponse.getAuthor()));

        verify(postService, times(1)).getAllUnpublishedPosts();
    }

    @Test
    void testGetAllPublishedPosts() throws Exception {
        PostResponse postResponse = new PostResponse(1L, "Title", "Content", "Author", null, null, null);
        when(postService.getAllPublishedPosts()).thenReturn(List.of(postResponse));

        mockMvc.perform(get("/api/posts/published"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(postResponse.getTitle()))
                .andExpect(jsonPath("$[0].content").value(postResponse.getContent()))
                .andExpect(jsonPath("$[0].author").value(postResponse.getAuthor()));

        verify(postService, times(1)).getAllPublishedPosts();
    }

    @Test
    void testGetPostById() throws Exception {
        PostResponse postResponse = new PostResponse(1L, "Title", "Content", "Author", null, null, null);
        when(postService.getPostById(1L)).thenReturn(postResponse);

        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(postResponse.getTitle()))
                .andExpect(jsonPath("$.content").value(postResponse.getContent()))
                .andExpect(jsonPath("$.author").value(postResponse.getAuthor()));

        verify(postService, times(1)).getPostById(1L);
    }

    @Test
    void testAddPost() throws Exception {
        PostRequest postRequest = new PostRequest("Title", "Content", "Author", null, null);
        when(postService.addPost(postRequest)).thenReturn(1L);

        String postRequestString = objectMapper.writeValueAsString(postRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postRequestString))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/post/1"));

        verify(postService, times(1)).addPost(postRequest);
    }

    @Test
    void testUpdatePost() throws Exception {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest("Updated Title", "Updated Content", "Updated Author", null);
        doNothing().when(postService).updatePostById(1L, updatablePostRequest);

        String updatablePostString = objectMapper.writeValueAsString(updatablePostRequest);

        mockMvc.perform(put("/api/posts/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatablePostString))
                .andExpect(status().isOk());

        verify(postService, times(1)).updatePostById(1L, updatablePostRequest);
    }

    @Test
    void testUpdatePostStatus() throws Exception {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest(null);
        doNothing().when(postService).updatePostStatusById(1L, updatePostStatusRequest);

        String updatePostStatusString = objectMapper.writeValueAsString(updatePostStatusRequest);

        mockMvc.perform(put("/api/posts/1/update-poststatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePostStatusString))
                .andExpect(status().isOk());

        verify(postService, times(1)).updatePostStatusById(1L, updatePostStatusRequest);
    }

    @Test
    void testPublishPost() throws Exception {
        doNothing().when(postService).publishPostById(1L);

        mockMvc.perform(put("/api/posts/1/publish"))
                .andExpect(status().isOk());

        verify(postService, times(1)).publishPostById(1L);
    }
}
