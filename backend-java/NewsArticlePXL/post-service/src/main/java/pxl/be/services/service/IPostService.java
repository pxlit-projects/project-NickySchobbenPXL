package pxl.be.services.service;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.domain.dto.UpdatablePostRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;

import java.util.List;

public interface IPostService {
    List<PostResponse> getAllUnpublishedPosts();
    List<PostResponse> getAllPublishedPosts();

    PostResponse getPostById(Long postId);

    void deletePostById(Long postId);

    Long addPost(PostRequest postRequest);

    void updatePostById(Long postId, UpdatablePostRequest updatablePost);

    void updatePostStatusById(Long postId, UpdatePostStatusRequest updatePostStatusRequest);

    void publishPostById(Long postId);
}
