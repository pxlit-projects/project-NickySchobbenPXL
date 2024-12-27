package pxl.be.services.services;

import pxl.be.services.domain.dto.CommentRequest;
import pxl.be.services.domain.dto.CommentResponse;
import pxl.be.services.domain.dto.UpdatableCommentRequest;

import java.util.List;

public interface ICommentService {

    List<CommentResponse> getAllCommentsForPost(Long postId);
    CommentResponse createNewComment(Long postId, CommentRequest commentRequest);

    void deleteComment(Long commentId);

    CommentResponse updateComment(Long commentId, UpdatableCommentRequest updatableCommentRequest);
}
