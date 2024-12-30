package pxl.be.services.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewStatusTests {

    @Test
    void testReviewStatusEnumValues() {
        assertEquals(2, ReviewStatus.values().length);
        assertEquals("APPROVED", ReviewStatus.APPROVED.name());
        assertEquals("DENIED", ReviewStatus.DENIED.name());
    }

    @Test
    void testReviewStatusEnumValuesByOrdinal() {
        assertEquals(ReviewStatus.APPROVED, ReviewStatus.values()[0]);
        assertEquals(ReviewStatus.DENIED, ReviewStatus.values()[1]);
    }

    @Test
    void testReviewStatusEnumValueOf() {
        assertEquals(ReviewStatus.APPROVED, ReviewStatus.valueOf("APPROVED"));
        assertEquals(ReviewStatus.DENIED, ReviewStatus.valueOf("DENIED"));
    }

    @Test
    void testReviewStatusEnumInvalidValueOf() {
        assertThrows(IllegalArgumentException.class, () -> ReviewStatus.valueOf("INVALID_STATUS"));
    }
}
