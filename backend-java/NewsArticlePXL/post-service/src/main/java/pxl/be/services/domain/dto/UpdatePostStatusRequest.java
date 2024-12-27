package pxl.be.services.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pxl.be.services.domain.PostStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostStatusRequest {
    private PostStatus postStatus;
}
