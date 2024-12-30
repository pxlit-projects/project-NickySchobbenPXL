import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PostService } from './post.service';
import { Post } from '../../../../core/models/posts/Post';
import { CreatePostForm } from '../../../../core/models/posts/CreatePostForm';
import { UpdatablePost } from '../../../../core/models/posts/UpdatablePost';
import { HttpErrorResponse } from '@angular/common/http';

describe('PostService', () => {
  let service: PostService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PostService]
    });

    service = TestBed.inject(PostService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();  // Ensure there are no outstanding HTTP requests
  });

  it('should retrieve all unpublished posts', () => {
    const mockPosts: Post[] = [
      new Post(1, 'Post 1', 'Content 1', 'Author 1', new Date(), 'unpublished', 'category1'),
      new Post(2, 'Post 2', 'Content 2', 'Author 2', new Date(), 'unpublished', 'category2')
    ];

    service.getAllUnpublishedPosts().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(mockPosts);
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/unpublished');
    expect(req.request.method).toBe('GET');
    req.flush(mockPosts);  // Mock the response
  });

  it('should retrieve all published posts', () => {
    const mockPosts: Post[] = [
      new Post(1, 'Post 1', 'Content 1', 'Author 1', new Date(), 'published', 'category1'),
      new Post(2, 'Post 2', 'Content 2', 'Author 2', new Date(), 'published', 'category2')
    ];

    service.getAllPublishedPosts().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(mockPosts);
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/published');
    expect(req.request.method).toBe('GET');
    req.flush(mockPosts);  // Mock the response
  });

  it('should retrieve a post by ID', () => {
    const mockPost = new Post(1, 'Post 1', 'Content 1', 'Author 1', new Date(), 'published', 'category1');

    service.getPostById(1).subscribe(post => {
      expect(post).toEqual(mockPost);
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockPost);  // Mock the response
  });

  it('should update a post by ID', () => {
    const updatablePost = new UpdatablePost('Updated Post', 'Updated Author', 'Updated Content', 'updatedCategory');

    service.updatePostById(1, updatablePost);

    const req = httpMock.expectOne('http://localhost:8081/api/posts/1/update');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatablePost);
  });

  it('should publish a post by ID', () => {
    service.publishPost(1);

    const req = httpMock.expectOne('http://localhost:8081/api/posts/1/publish');
    expect(req.request.method).toBe('PUT');
  });

  it('should handle errors gracefully', () => {
    const mockError = new HttpErrorResponse({
      error: 'error message',
      status: 500,
      statusText: 'Server Error',
      url: 'http://localhost:8081/api/posts/unpublished'
    });

    service.getAllUnpublishedPosts().subscribe({
      next: () => fail('Expected an error, but got data'),
      error: (error) => {
        expect(error.message).toContain('something bad happened');
      }
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/unpublished');
    req.flush('Error', mockError);  // Simulate error response
  });
});
