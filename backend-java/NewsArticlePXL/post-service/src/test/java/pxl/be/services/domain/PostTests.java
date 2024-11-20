package pxl.be.services.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PostTests {

    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post();
    }

    @Test
    void testPost_NoArgsConstructor() {
        assertThat(post).isNotNull();
        assertThat(post.getAuthor()).isNullOrEmpty();
        assertThat(post.getContent()).isNullOrEmpty();
        assertThat(post.getTitle()).isNullOrEmpty();
    }

    @Test
    void testPost_AllArgsConstructor() {
        LocalDate localDate = LocalDate.of(2024, 2, 14);
        Post post = new Post(1L,"testTitle", "testContent", "testAuthor", localDate);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("testTitle");
        assertThat(post.getContent()).isEqualTo("testContent");
        assertThat(post.getAuthor()).isEqualTo("testAuthor");
        assertThat(post.getDate()).isEqualTo(localDate);
    }
}
