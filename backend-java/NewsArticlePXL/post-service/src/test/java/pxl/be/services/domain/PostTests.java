package pxl.be.services.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PostTests {

    @Test
    void testPostConstructorAndGetters() {
        Post post = new Post(1L, "Post Title", "Post Content", "Author", LocalDate.now(), PostStatus.PUBLISHED, PostCategory.POLITICS);

        assertEquals(1L, post.getId());
        assertEquals("Post Title", post.getTitle());
        assertEquals("Post Content", post.getContent());
        assertEquals("Author", post.getAuthor());
        assertNotNull(post.getDate());
        assertEquals(PostStatus.PUBLISHED, post.getPostStatus());
        assertEquals(PostCategory.POLITICS, post.getCategory());
    }

    @Test
    void testPostBuilder() {
        Post post = Post.builder()
                .id(1L)
                .title("Post Title")
                .content("Post Content")
                .author("Author")
                .date(LocalDate.now())
                .postStatus(PostStatus.PUBLISHED)
                .category(PostCategory.POLITICS)
                .build();

        assertEquals(1L, post.getId());
        assertEquals("Post Title", post.getTitle());
        assertEquals("Post Content", post.getContent());
        assertEquals("Author", post.getAuthor());
        assertNotNull(post.getDate());
        assertEquals(PostStatus.PUBLISHED, post.getPostStatus());
        assertEquals(PostCategory.POLITICS, post.getCategory());
    }

    @Test
    void testSetters() {
        Post post = new Post();
        post.setId(2L);
        post.setTitle("New Title");
        post.setContent("New Content");
        post.setAuthor("New Author");
        post.setDate(LocalDate.now());
        post.setPostStatus(PostStatus.CONCEPT);
        post.setCategory(PostCategory.REGIONAL);

        assertEquals(2L, post.getId());
        assertEquals("New Title", post.getTitle());
        assertEquals("New Content", post.getContent());
        assertEquals("New Author", post.getAuthor());
        assertNotNull(post.getDate());
        assertEquals(PostStatus.CONCEPT, post.getPostStatus());
        assertEquals(PostCategory.REGIONAL, post.getCategory());
    }
}
