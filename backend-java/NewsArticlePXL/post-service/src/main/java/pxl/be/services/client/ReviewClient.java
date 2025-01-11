package pxl.be.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review-service")
public interface ReviewClient {

    @DeleteMapping("/api/review/{postId}/delete-all")
    void deleteAllReviewsForPostByPostId(@PathVariable Long postId);
}
