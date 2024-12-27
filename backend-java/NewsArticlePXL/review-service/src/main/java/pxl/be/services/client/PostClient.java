package pxl.be.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;

@FeignClient(name = "post-service")
public interface PostClient {

    @PutMapping("/api/posts/{chatId}/update-poststatus")
    void updatePostStatus(@PathVariable Long chatId, @RequestBody UpdatePostStatusRequest postStatusRequest);
}
