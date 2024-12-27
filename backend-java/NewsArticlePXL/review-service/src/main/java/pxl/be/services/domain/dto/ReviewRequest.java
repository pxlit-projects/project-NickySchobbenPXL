package pxl.be.services.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pxl.be.services.domain.ReviewStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private Long postId;
    private String reviewerName;
    private String reviewContent;
    private ReviewStatus reviewStatus;
}
