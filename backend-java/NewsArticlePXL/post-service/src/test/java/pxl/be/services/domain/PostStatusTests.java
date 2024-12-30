package pxl.be.services.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostStatusTests {

    @Test
    void testEnumValues() {
        assertEquals(4, PostStatus.values().length);
        assertEquals(PostStatus.PUBLISHED, PostStatus.valueOf("PUBLISHED"));
        assertEquals(PostStatus.APPROVED, PostStatus.valueOf("APPROVED"));
        assertEquals(PostStatus.CONCEPT, PostStatus.valueOf("CONCEPT"));
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, PostStatus.valueOf("WAITING_FOR_APPROVAL"));
    }

    @Test
    void testEnumToString() {
        assertEquals("PUBLISHED", PostStatus.PUBLISHED.name());
        assertEquals("APPROVED", PostStatus.APPROVED.name());
        assertEquals("CONCEPT", PostStatus.CONCEPT.name());
        assertEquals("WAITING_FOR_APPROVAL", PostStatus.WAITING_FOR_APPROVAL.name());
    }

    @Test
    void testEnumFromString() {
        assertEquals(PostStatus.PUBLISHED, PostStatus.valueOf("PUBLISHED"));
        assertEquals(PostStatus.APPROVED, PostStatus.valueOf("APPROVED"));
        assertEquals(PostStatus.CONCEPT, PostStatus.valueOf("CONCEPT"));
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, PostStatus.valueOf("WAITING_FOR_APPROVAL"));
    }

    @Test
    void testEnumContainsAllValues() {
        for (PostStatus status : PostStatus.values()) {
            assertNotNull(PostStatus.valueOf(status.name()));
        }
    }
}
