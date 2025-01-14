package pxl.be.services.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.Comment;
import pxl.be.services.domain.dto.CommentRequest;
import pxl.be.services.domain.dto.CommentResponse;
import pxl.be.services.domain.dto.UpdatableCommentRequest;
import pxl.be.services.exception.CommentNotFoundException;
import pxl.be.services.repository.CommentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    @Override
    public List<CommentResponse> getAllCommentsForPost(Long postId) {
        return commentRepository.findAll().stream()
                .filter(comment -> Objects.equals(comment.getPostId(), postId))
                .map(this::mapCommentEntityToCommentResponse)
                .toList();
    }

    @Override
    public CommentResponse createNewComment(Long postId, CommentRequest commentRequest) {
        Comment comment = Comment.builder()
                .postId(postId)
                .commenter(commentRequest.getCommenter())
                .description(commentRequest.getDescription())
                .dateOfComment(LocalDate.now())
                .build();

        commentRepository.save(comment);
        return mapCommentEntityToCommentResponse(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    public CommentResponse updateComment(Long commentId, UpdatableCommentRequest updatableCommentRequest) {
        Comment comment = getCommentById(commentId);
        comment.setDescription(updatableCommentRequest.getDescription());
        commentRepository.save(comment);
        return mapCommentEntityToCommentResponse(comment);
    }

    @Override
    public void deleteAllCommentsForPostByPostId(Long postId) {
        LOGGER.info("Attempting to delete all comments for post with postId " + postId);
        List<Comment> listOfComments = commentRepository.findAll().stream().filter(comment -> Objects.equals(comment.getPostId(), postId)).toList();
        commentRepository.deleteAll(listOfComments);
        LOGGER.info("Deleting comments for post with postId: " + postId + " has been successful!");
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id: " + commentId + " cannot be found"));
    }

    public CommentResponse mapCommentEntityToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .postId(comment.getPostId())
                .commenter(comment.getCommenter())
                .description(comment.getDescription())
                .dateOfComment(comment.getDateOfComment())
                .build();
    }
}
