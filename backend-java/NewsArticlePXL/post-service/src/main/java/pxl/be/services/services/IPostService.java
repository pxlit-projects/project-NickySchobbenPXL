package pxl.be.services.services;

import pxl.be.services.domain.Post;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;

import java.util.List;

public interface IPostService {

    List<PostResponse> getAllPosts();

    PostResponse getPostById(Long id);

    void deletePost(Long id);

    Long addPost(PostRequest postRequest);

    void updatePostById(Long id, PostRequest postRequest);
}
