package pxl.be.services.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pxl.be.services.domain.Post;
import pxl.be.services.domain.PostStatus;
import pxl.be.services.domain.dto.PostRequest;
import pxl.be.services.domain.dto.PostResponse;
import pxl.be.services.domain.dto.UpdatablePostRequest;
import pxl.be.services.domain.dto.UpdatePostStatusRequest;
import pxl.be.services.exception.PostNotFoundException;
import pxl.be.services.repository.PostRepository;
import pxl.be.services.service.IPostService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    @Override
    public List<PostResponse> getAllUnpublishedPosts() {
        return postRepository.findAll().stream().filter(post -> post.getPostStatus() != PostStatus.PUBLISHED).map(this::mapPostEntityToPostResponse).toList();
    }

    @Override
    public List<PostResponse> getAllPublishedPosts() {
        return postRepository.findAll().stream().filter(post -> post.getPostStatus() == PostStatus.PUBLISHED).map(this::mapPostEntityToPostResponse).toList();
    }

    @Override
    public PostResponse getPostById(Long postId) {
        return mapPostEntityToPostResponse(findPostById(postId));
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.delete(findPostById(postId));
    }

    @Override
    public Long addPost(PostRequest postRequest) {
        Post post = mapPostRequestToPostEntity(postRequest);
        return postRepository.save(post).getId();
    }

    @Override
    public void updatePostById(Long postId, UpdatablePostRequest updatablePost) {
        Post post = findPostById(postId);
        post.setTitle(updatablePost.getTitle());
        post.setContent(updatablePost.getContent());
        post.setAuthor(updatablePost.getAuthor());
        post.setPostStatus(PostStatus.WAITING_FOR_APPROVAL);
        post.setCategory(updatablePost.getCategory());
        postRepository.save(post);
    }

    @Override
    public void updatePostStatusById(Long postId, UpdatePostStatusRequest updatePostStatusRequest) {
        LOGGER.info("Attempting to update poststatus for post with id " + postId + "\n");
        Post post = findPostById(postId);
        post.setPostStatus(updatePostStatusRequest.getPostStatus());
        postRepository.save(post);
        LOGGER.info("The poststatus for post with postId " + postId + " has been succesfully updated!");
    }

    @Override
    public void publishPostById(Long postId) {
        Post post = findPostById(postId);
        post.setPostStatus(PostStatus.PUBLISHED);
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
                .postStatus(postRequest.getPostStatus())
                .date(LocalDate.now())
                .category(postRequest.getCategory())
                .build();
    }

    public PostResponse mapPostEntityToPostResponse(Post post) {
        return PostResponse.builder()
                .Id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .date(post.getDate())
                .postStatus(post.getPostStatus())
                .category(post.getCategory())
                .build();
    }
}
