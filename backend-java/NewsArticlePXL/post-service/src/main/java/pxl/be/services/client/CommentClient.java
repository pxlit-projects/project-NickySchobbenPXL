package pxl.be.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comment-service")
public interface CommentClient {

    @DeleteMapping("/api/comment/{postId}/delete-all")
    void deleteAllCommentsForPostByPostId(@PathVariable Long postId);
}
