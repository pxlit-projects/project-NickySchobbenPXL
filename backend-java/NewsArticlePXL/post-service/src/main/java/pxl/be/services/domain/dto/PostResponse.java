package pxl.be.services.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pxl.be.services.domain.PostCategory;
import pxl.be.services.domain.PostStatus;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long Id;
    private String title;
    private String content;
    private String author;
    private LocalDate date;
    private PostStatus postStatus;
    private PostCategory category;
}
