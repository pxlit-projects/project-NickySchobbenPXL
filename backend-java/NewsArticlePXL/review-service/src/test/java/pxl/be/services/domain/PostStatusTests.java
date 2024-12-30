package pxl.be.services.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostStatusTests {

    @Test
    void testPostStatusEnumValues() {
        assertEquals(2, PostStatus.values().length);
        assertEquals("APPROVED", PostStatus.APPROVED.name());
        assertEquals("WAITING_FOR_APPROVAL", PostStatus.WAITING_FOR_APPROVAL.name());
    }

    @Test
    void testPostStatusEnumValuesByOrdinal() {
        assertEquals(PostStatus.APPROVED, PostStatus.values()[0]);
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, PostStatus.values()[1]);
    }

    @Test
    void testPostStatusEnumValueOf() {
        assertEquals(PostStatus.APPROVED, PostStatus.valueOf("APPROVED"));
        assertEquals(PostStatus.WAITING_FOR_APPROVAL, PostStatus.valueOf("WAITING_FOR_APPROVAL"));
    }

    @Test
    void testPostStatusEnumInvalidValueOf() {
        assertThrows(IllegalArgumentException.class, () -> PostStatus.valueOf("INVALID_STATUS"));
    }
}
