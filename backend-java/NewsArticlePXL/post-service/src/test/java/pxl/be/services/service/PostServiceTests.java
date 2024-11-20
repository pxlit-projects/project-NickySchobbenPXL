package pxl.be.services.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pxl.be.services.domain.Post;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.repository.PostRepository;
import pxl.be.services.services.impl.PostService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        LocalDate date = LocalDate.of(2024, 2, 14);
        postOne = Post.builder()
                .title("testTitleOne")
                .content("testContentOne")
                .author("testAuthorOne")
                .date(date)
                .build();

        postTwo = Post.builder()
                .title("testTitleTwo")
                .content("testContentTwo")
                .author("testAuthorTwo")
                .date(date)
                .build();

        postRequest = PostRequest.builder()
                .title("testTitleRequest")
                .content("testContentRequest")
                .author("testAuthorRequest")
                .date(date)
                .build();
    }

    @Test
    public void testGetAllPosts() {
        List<Post> listOfPosts = Arrays.asList(postOne, postTwo);
        when(postRepository.findAll()).thenReturn(listOfPosts);

        List<PostResponse> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(postOne.getTitle(), result.get(0).getTitle());
        assertEquals(postTwo.getTitle(), result.get(1).getTitle());
    }

    @Test
    public void testAddPost() {
        when(postRepository.save(any(Post.class))).thenReturn(postOne);
        Long result = postService.addPost(postRequest);
        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result);
    }
}
