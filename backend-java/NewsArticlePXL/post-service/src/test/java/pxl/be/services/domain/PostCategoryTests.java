package pxl.be.services.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostCategoryTests {

    @Test
    void testEnumValues() {
        assertEquals(4, PostCategory.values().length);
        assertEquals(PostCategory.SPORTS, PostCategory.valueOf("SPORTS"));
        assertEquals(PostCategory.POLITICS, PostCategory.valueOf("POLITICS"));
        assertEquals(PostCategory.REGIONAL, PostCategory.valueOf("REGIONAL"));
        assertEquals(PostCategory.SCIENCE, PostCategory.valueOf("SCIENCE"));
    }

    @Test
    void testEnumToString() {
        assertEquals("SPORTS", PostCategory.SPORTS.name());
        assertEquals("POLITICS", PostCategory.POLITICS.name());
        assertEquals("REGIONAL", PostCategory.REGIONAL.name());
        assertEquals("SCIENCE", PostCategory.SCIENCE.name());
    }

    @Test
    void testEnumFromString() {
        assertEquals(PostCategory.SPORTS, PostCategory.valueOf("SPORTS"));
        assertEquals(PostCategory.POLITICS, PostCategory.valueOf("POLITICS"));
        assertEquals(PostCategory.REGIONAL, PostCategory.valueOf("REGIONAL"));
        assertEquals(PostCategory.SCIENCE, PostCategory.valueOf("SCIENCE"));
    }

    @Test
    void testEnumContainsAllValues() {
        for (PostCategory category : PostCategory.values()) {
            assertNotNull(PostCategory.valueOf(category.name()));
        }
    }
}
