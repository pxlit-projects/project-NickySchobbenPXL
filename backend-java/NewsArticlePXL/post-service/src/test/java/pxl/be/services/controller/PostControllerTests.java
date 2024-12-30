package pxl.be.services.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.domain.dto.UpdatablePostRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;
import pxl.be.services.service.IPostService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PostControllerTests {

    @Mock
    private IPostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUnpublishedPosts() {
        PostResponse postResponse = new PostResponse(1L, "Title", "Content", "Author", null, null, null);
        when(postService.getAllUnpublishedPosts()).thenReturn(Arrays.asList(postResponse));

        ResponseEntity<List<PostResponse>> responseEntity = postController.getAllUnpublishedPosts();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).hasSize(1);
        verify(postService, times(1)).getAllUnpublishedPosts();
    }


    @Test
    void getAllPublishedPosts() {
        PostResponse postResponse = new PostResponse(1L, "Title", "Content", "Author", null, null, null);
        when(postService.getAllPublishedPosts()).thenReturn(List.of(postResponse));

        ResponseEntity<List<PostResponse>> responseEntity = postController.getAllPublishedPosts();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size());
        verify(postService, times(1)).getAllPublishedPosts();
    }

    @Test
    void getPostById() {
        PostResponse postResponse = new PostResponse(1L, "Title", "Content", "Author", null, null, null);
        when(postService.getPostById(1L)).thenReturn(postResponse);

        ResponseEntity<PostResponse> responseEntity = postController.getPostById(1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals("Title", Objects.requireNonNull(responseEntity.getBody()).getTitle());
        verify(postService, times(1)).getPostById(1L);
    }

    @Test
    void addPost() {
        PostRequest postRequest = new PostRequest("Title", "Content", "Author", null, null);
        when(postService.addPost(postRequest)).thenReturn(1L);

        ResponseEntity<Long> responseEntity = postController.addPost(postRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertEquals("/api/post/1", Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString());
        verify(postService, times(1)).addPost(postRequest);
    }

    @Test
    void updatePost() {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest("Updated Title", "Updated Content", "Updated Author", null);
        doNothing().when(postService).updatePostById(1L, updatablePostRequest);

        ResponseEntity<Void> responseEntity = postController.updatePost(1L, updatablePostRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(postService, times(1)).updatePostById(1L, updatablePostRequest);
    }

    @Test
    void updatePostStatus() {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest(null);
        doNothing().when(postService).updatePostStatusById(1L, updatePostStatusRequest);

        ResponseEntity<Void> responseEntity = postController.updatePostStatus(1L, updatePostStatusRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(postService, times(1)).updatePostStatusById(1L, updatePostStatusRequest);
    }

    @Test
    void publishPost() {
        doNothing().when(postService).publishPostById(1L);

        ResponseEntity<Void> responseEntity = postController.publishPost(1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(postService, times(1)).publishPostById(1L);
    }
}
