package pxl.be.services.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.services.IPostService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    private final IPostService postService;

    @PostMapping
    public ResponseEntity<Long> addPost(@RequestBody PostRequest postRequest) {
        Long id = postService.addPost(postRequest);
        return ResponseEntity.created(URI.create("/api/post/" + id)).build();
    }

    @GetMapping("/published")
    public ResponseEntity<List<PostResponse>> getAllPublishedPosts() {
        return ResponseEntity.ok(postService.getAllPublishedPosts());
    }

    @GetMapping("/concept")
    public ResponseEntity<List<PostResponse>> getAllConceptPosts() {
        return ResponseEntity.ok(postService.getAllConceptPosts());
    }

    @GetMapping("/unpublished")
    public ResponseEntity<List<PostResponse>> getAllUnpublishedPosts() {
        return ResponseEntity.ok(postService.getAllUnpublishedPosts());
    }

    // TEMPORARY FOR TESTING
    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
