package pxl.be.services.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.Post;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.exception.PostNotFoundException;
import pxl.be.services.repository.PostRepository;
import pxl.be.services.services.IPostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(this::mapPostEntityToPostResponse).toList();
    }

    @Override
    public PostResponse getPostById(Long id) {
        return mapPostEntityToPostResponse(findPostById(id));
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.delete(findPostById(id));
    }

    @Override
    public Long addPost(PostRequest postRequest) {
        Post post = mapPostRequestToPostEntity(postRequest);
        return postRepository.save(post).getId();
    }

    @Override
    public void updatePostById(Long id, PostRequest postRequest) {
        Post post = findPostById(id);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());
        post.setDate(postRequest.getDate());

        postRepository.save(post);
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post with id " + id + " cannot be found"));
    }


    public Post mapPostRequestToPostEntity(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(postRequest.getAuthor())
                .date(postRequest.getDate())
                .build();
    }

    public PostResponse mapPostEntityToPostResponse(Post post) {
        return PostResponse.builder()
                .Id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .date(post.getDate())
                .published(post.isPublished())
                .build();
    }
}
