package pxl.be.services.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pxl.be.services.domain.Post;
import pxl.be.services.domain.PostStatus;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.exception.PostNotFoundException;
import pxl.be.services.repository.PostRepository;
import pxl.be.services.services.impl.PostService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes= PostServiceTests.class)
public class PostServiceTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post postOne;
    private Post postTwo;

    private PostRequest postRequest;

    @BeforeEach
    void setup() {
        postOne = Post.builder()
                .id(1L)
                .title("testTitleOne")
                .content("testContentOne")
                .author("testAuthorOne")
                .date(LocalDate.of(2024, 2, 14))
                .postStatus(PostStatus.CONCEPT)
                .build();

        postTwo = Post.builder()
                .id(2L)
                .title("testTitleTwo")
                .content("testContentTwo")
                .author("testAuthorTwo")
                .date(LocalDate.of(2024, 2, 14))
                .postStatus(PostStatus.WAITING_FOR_APPROVAL)
                .build();

        postRequest = PostRequest.builder()
                .title("testTitleRequest")
                .content("testContentRequest")
                .author("testAuthorRequest")
                .postStatus(PostStatus.APPROVED)
                .build();
    }

    @Test
    void testGetAllPosts_Success() {
        List<Post> listOfPosts = Arrays.asList(postOne, postTwo);
        when(postRepository.findAll()).thenReturn(listOfPosts);

        List<PostResponse> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(postOne.getTitle(), result.get(0).getTitle());
        assertEquals(postTwo.getTitle(), result.get(1).getTitle());
    }

    @Test
    void testAddPost_Success() {
        when(postRepository.save(any(Post.class))).thenReturn(postOne);
        Long result = postService.addPost(postRequest);
        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result);
    }

    @Test
    void testGetPostById_Success() {
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.of(postOne));
        PostResponse response = postService.getPostById(postOne.getId());
        assertEquals(response.getTitle(), postOne.getTitle());
        assertEquals(response.getContent(), postOne.getContent());
        assertEquals(response.getAuthor(), postOne.getAuthor());
        assertEquals(response.getDate(), postOne.getDate());

        verify(postRepository, times(1)).findById(postOne.getId());
    }

    @Test
    void testGetPostByIdWithWrongId_ThrowPostNotFoundException() {
        Long id = postOne.getId();
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> postService.getPostById(id));
        assertEquals("Post with id " + id + " cannot be found", exception.getMessage());
    }

    @Test
    void testDeletePostByIdWithCorrectId_Success() {
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.of(postOne));
        postService.deletePostById(postOne.getId());

        verify(postRepository, times(1)).findById(postOne.getId());
        verify(postRepository, times(1)).delete(postOne);
    }

    @Test
    void testDeletePostByIdWithWrongId_ThrowPostNotFoundException() {
        Long id = postOne.getId();
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postService.deletePostById(id));
        verify(postRepository, times(1)).findById(id);
        verify(postRepository, never()).delete(any(Post.class));
    }

    @Test
    public void testUpdatePostById() {
        Long id = postOne.getId();
        postRequest = PostRequest.builder()
                .title("newTitle")
                .content("newContent")
                .author("newAuthor")
                .build();

        when(postRepository.findById(id)).thenReturn(Optional.of(postOne));
        when(postRepository.save(any(Post.class))).thenReturn(postOne);
        postService.updatePostById(id, postRequest);
        verify(postRepository, times(1)).save(postOne);
        assertEquals("newTitle", postOne.getTitle());
        assertEquals("newContent", postOne.getContent());
        assertEquals("newAuthor", postOne.getAuthor());
        assertEquals(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()), postOne.getDate());
    }

    @Test
    public void testUpdatePostById_PostNotFound() {
        postRequest = PostRequest.builder()
                .title("newTitle")
                .content("newContent")
                .author("newAuthor")
                .build();

        when(postRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postService.updatePostById(999L, postRequest));
    }
}
