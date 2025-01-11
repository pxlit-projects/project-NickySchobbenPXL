package pxl.be.services.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pxl.be.services.domain.Post;
import pxl.be.services.domain.PostCategory;
import pxl.be.services.domain.PostStatus;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.domain.dto.UpdatablePostRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;
import pxl.be.services.exception.PostNotFoundException;
import pxl.be.services.repository.PostRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PostServiceTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post post;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        post = new Post(1L, "Title", "Content", "Author", LocalDate.now(), PostStatus.CONCEPT, PostCategory.SPORTS);
    }

    @Test
    void testGetAllUnpublishedPosts() {
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<PostResponse> result = postService.getAllUnpublishedPosts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(PostStatus.CONCEPT, result.get(0).getPostStatus());
    }

    @Test
    void testGetAllPublishedPosts() {
        post.setPostStatus(PostStatus.PUBLISHED);
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<PostResponse> result = postService.getAllPublishedPosts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(PostStatus.PUBLISHED, result.get(0).getPostStatus());
    }

    @Test
    void testGetPostById() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        PostResponse result = postService.getPostById(1L);

        assertNotNull(result);
        assertEquals(post.getId(), result.getId());
        assertEquals(post.getTitle(), result.getTitle());
    }

    @Test
    void testGetPostByIdNotFound() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.getPostById(1L));
    }

    @Test
    void testAddPost() {
        PostRequest postRequest = new PostRequest("Title", "Content", "Author", PostStatus.CONCEPT, PostCategory.SPORTS);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Long postId = postService.addPost(postRequest);

        assertNotNull(postId);
        assertEquals(1L, postId);
    }

    @Test
    void testUpdatePostById() {
        UpdatablePostRequest updatablePostRequest = new UpdatablePostRequest("Updated Title", "Updated Content", "Updated Author", PostCategory.SCIENCE);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        postService.updatePostById(1L, updatablePostRequest);

        assertEquals("Updated Title", post.getTitle());
        assertEquals("Updated Content", post.getContent());
        assertEquals("Updated Author", post.getAuthor());
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, post.getPostStatus());
    }

    @Test
    void testUpdatePostStatusById() {
        UpdatePostStatusRequest updatePostStatusRequest = new UpdatePostStatusRequest(PostStatus.PUBLISHED);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        postService.updatePostStatusById(1L, updatePostStatusRequest);

        assertEquals(PostStatus.PUBLISHED, post.getPostStatus());
    }

    @Test
    void testPublishPostById() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        postService.publishPostById(1L);

        assertEquals(PostStatus.PUBLISHED, post.getPostStatus());
    }

    @Test
    void testFindPostById() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        Post result = postService.findPostById(1L);

        assertNotNull(result);
        assertEquals(post.getId(), result.getId());
    }

    @Test
    void testFindPostByIdNotFound() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.findPostById(1L));
    }

    @Test
    void testMapPostRequestToPostEntity() {
        PostRequest postRequest = new PostRequest("Title", "Content", "Author", PostStatus.CONCEPT, PostCategory.SPORTS);

        Post postEntity = postService.mapPostRequestToPostEntity(postRequest);

        assertNotNull(postEntity);
        assertEquals("Title", postEntity.getTitle());
        assertEquals("Content", postEntity.getContent());
        assertEquals("Author", postEntity.getAuthor());
        assertEquals(PostStatus.CONCEPT, postEntity.getPostStatus());
        assertEquals(PostCategory.SPORTS, postEntity.getCategory());
    }

    @Test
    void testMapPostEntityToPostResponse() {
        PostResponse postResponse = postService.mapPostEntityToPostResponse(post);

        assertNotNull(postResponse);
        assertEquals(post.getId(), postResponse.getId());
        assertEquals(post.getTitle(), postResponse.getTitle());
        assertEquals(post.getContent(), postResponse.getContent());
        assertEquals(post.getAuthor(), postResponse.getAuthor());
    }
}
