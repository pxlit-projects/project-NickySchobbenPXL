package pxl.be.services.services;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;

import java.util.List;

public interface IPostService {

    // TEMPORARY FUNCTION FOR TESTING
    List<PostResponse> getAllPosts();

    List<PostResponse> getAllPublishedPosts();

    List<PostResponse> getAllConceptPosts();

    List<PostResponse> getAllUnpublishedPosts();

    PostResponse getPostById(Long id);

    void deletePostById(Long id);

    Long addPost(PostRequest postRequest);

    void updatePostById(Long id, PostRequest postRequest);
}
