package pxl.be.services.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxl.be.services.domain.dto.CommentRequest;
import pxl.be.services.domain.dto.CommentResponse;
import pxl.be.services.domain.dto.UpdatableCommentRequest;
import pxl.be.services.services.ICommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private final ICommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsByPostId(@PathVariable Long postId) {
        LOGGER.info("Now fetching all comments for post with postId: " + postId);
        return ResponseEntity.ok(commentService.getAllCommentsForPost(postId));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponse> addNewComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest) {
        LOGGER.info("Now adding a new comment to post with postId: " + postId);
        return ResponseEntity.ok(commentService.createNewComment(postId, commentRequest));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody UpdatableCommentRequest updatableCommentRequest) {
        LOGGER.info("Now changing the comment for comment with commentId: " + commentId);
        return ResponseEntity.ok(commentService.updateComment(commentId, updatableCommentRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        LOGGER.info("Now deleting comment with id: " + commentId);
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
