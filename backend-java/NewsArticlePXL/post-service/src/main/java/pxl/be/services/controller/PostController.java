package pxl.be.services.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.domain.dto.UpdatablePostRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;
import pxl.be.services.service.IPostService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    private final IPostService postService;

    @GetMapping("/unpublished")
    public ResponseEntity<List<PostResponse>> getAllUnpublishedPosts() {
        LOGGER.info("Attempting to fetch unpublished posts");
        return ResponseEntity.ok(postService.getAllUnpublishedPosts());
    }

    @GetMapping("/published")
    public ResponseEntity<List<PostResponse>> getAllPublishedPosts() {
        LOGGER.info("Attempting to fetch published posts");
        return ResponseEntity.ok(postService.getAllPublishedPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        LOGGER.info("Attempting to fetch post with id " + postId);
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseEntity<Long> addPost(@RequestBody PostRequest postRequest) {
        LOGGER.info("Attempting to add post");
        Long id = postService.addPost(postRequest);
        return ResponseEntity.created(URI.create("/api/post/" + id)).build();
    }

    @PutMapping("/{postId}/update")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatablePostRequest updatablePost) {
        postService.updatePostById(postId, updatablePost);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/update-poststatus")
    public ResponseEntity<Void> updatePostStatus(@PathVariable Long postId, @RequestBody UpdatePostStatusRequest updatePostStatusRequest) {
        LOGGER.info("Request to update the poststatus of post with id: " + postId + " has been received!");
        postService.updatePostStatusById(postId, updatePostStatusRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/publish")
    public ResponseEntity<Void> publishPost(@PathVariable Long postId) {
        LOGGER.info("Publishing post with postId: " + postId);
        postService.publishPostById(postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }
}