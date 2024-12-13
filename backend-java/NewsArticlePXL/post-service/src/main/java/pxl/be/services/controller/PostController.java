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
@CrossOrigin(origins = "*")
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    private final IPostService postService;

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long chatId) {
        return ResponseEntity.ok(postService.getPostById(chatId));
    }

    @PostMapping
    public ResponseEntity<Long> addPost(@RequestBody PostRequest postRequest) {
        Long id = postService.addPost(postRequest);
        return ResponseEntity.created(URI.create("/api/post/" + id)).build();
    }
}
