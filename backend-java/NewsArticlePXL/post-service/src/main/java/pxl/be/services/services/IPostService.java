package pxl.be.services.services;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.domain.dto.UpdatablePostRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;

import java.util.List;

public interface IPostService {

    // TEMPORARY FUNCTION FOR TESTING
    List<PostResponse> getAllPosts();

    List<PostResponse> getAllPublishedPosts();

    List<PostResponse> getAllConceptPosts();

    List<PostResponse> getAllUnpublishedPosts();

    PostResponse getPostById(Long postId);

    void deletePostById(Long postId);

    Long addPost(PostRequest postRequest);

    void updatePostById(Long postId, UpdatablePostRequest updatablePost);

    void updatePostStatusById(Long postId, UpdatePostStatusRequest updatePostStatusRequest);

    void publishPostById(Long postId);
}
