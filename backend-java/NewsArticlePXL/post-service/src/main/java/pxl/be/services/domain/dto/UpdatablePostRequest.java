package pxl.be.services.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pxl.be.services.domain.PostCategory;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatablePostRequest {
    private String title;
    private String content;
    private String author;
    private PostCategory category;
}
