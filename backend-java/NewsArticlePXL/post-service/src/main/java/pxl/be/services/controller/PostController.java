package pxl.be.services.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.services.IPostService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private final IPostService postService;

    @PostMapping
    public ResponseEntity<Long> addPost(@RequestBody PostRequest postRequest) {
        Long id = postService.addPost(postRequest);
        return ResponseEntity.created(URI.create("/api/post/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
