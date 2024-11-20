package pxl.be.services.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.repository.PostRepository;
import pxl.be.services.services.IPostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(post -> new PostResponse()).toList();
    }

    @Override
    public PostResponse getPostById(Long id) {
        return null;
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public Long addPost(PostRequest postRequest) {
        return null;
    }

    @Override
    public void updatePostById(Long id, PostRequest postRequest) {
    }
}
