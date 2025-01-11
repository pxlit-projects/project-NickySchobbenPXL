import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PostService } from './post.service';
import { Post } from '../../../../core/models/posts/Post';
import { UpdatablePost } from '../../../../core/models/posts/UpdatablePost';
import { HttpErrorResponse } from '@angular/common/http';
import { CreatePostForm } from '../../../../core/models/posts/CreatePostForm';
import { CreatePost } from '../../../../core/models/posts/CreatePost';

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
    httpMock.verify();
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
    req.flush(mockPosts);
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
    req.flush(mockPosts);
  });

  it('should retrieve a post by ID', () => {
    const mockPost = new Post(1, 'Post 1', 'Content 1', 'Author 1', new Date(), 'published', 'category1');

    service.getPostById(1).subscribe(post => {
      expect(post).toEqual(mockPost);
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockPost);
  });

  it('should update a post by ID', () => {
    const updatablePost = new UpdatablePost('Updated Post', 'Updated Author', 'Updated Content', 'updatedCategory');

    service.updatePostById(1, updatablePost);

    const req = httpMock.expectOne('http://localhost:8081/api/posts/1/update');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatablePost);
    req.flush({});
  });

  it('should publish a post by ID', () => {
    service.publishPost(1);

    const req = httpMock.expectOne('http://localhost:8081/api/posts/1/publish');
    expect(req.request.method).toBe('PUT');
    req.flush({});
  });

  it('should create a new post', () => {
    const createPostForm: CreatePostForm = {
      title: 'New Post',
      author: 'Author 1',
      content: 'Post content',
      action: 'create',
      category: 'category1'
    };

    const createPost: CreatePost = new CreatePost(
      createPostForm.title,
      createPostForm.author,
      createPostForm.content,
      createPostForm.action,
      createPostForm.category
    );

    service.createNewPost(createPostForm).subscribe(() => {
      expect(true).toBeTrue();
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(createPost);
    req.flush({});
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
    req.flush('Error', mockError);
  });

  it('should filter unpublished posts by status', () => {
    const mockPosts: Post[] = [
      new Post(1, 'Post 1', 'Content 1', 'Author 1', new Date(), 'UNPUBLISHED', 'SPORTS'),
      new Post(2, 'Post 2', 'Content 2', 'Author 2', new Date(), 'PUBLISHED', 'SPORTS'),
      new Post(3, 'Post 3', 'Content 3', 'Author 3', new Date(), 'UNPUBLISHED', 'SPORTS')
    ];

    service.getAllUnpublishedPosts().subscribe(posts => {
      expect(posts.length).toBe(3);
      expect(posts[0].getPostStatus()).toBe('UNPUBLISHED');
      expect(posts[2].getPostStatus()).toBe('UNPUBLISHED');
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/unpublished');
    expect(req.request.method).toBe('GET');
    req.flush(mockPosts);
  });


  it('should filter published posts by category', () => {
    const mockPosts: Post[] = [
      new Post(1, 'Post 1', 'Content 1', 'Author 1', new Date(), 'published', 'category1'),
      new Post(2, 'Post 2', 'Content 2', 'Author 2', new Date(), 'published', 'category2')
    ];

    service.getPublishedPostsByCategory('category1').subscribe(posts => {
      expect(posts.length).toBe(1);
      expect(posts[0].getCategory()).toBe('category1');
    });

    const req = httpMock.expectOne('http://localhost:8081/api/posts/published');
    expect(req.request.method).toBe('GET');
    req.flush(mockPosts);
  });
});

describe('CreatePost', () => {
  it('should set postStatus to WAITING_FOR_APPROVAL when action is "Save"', () => {
    const createPost = new CreatePost('Post Title', 'Author', 'Content', 'Save', 'Category');

    expect(createPost.postStatus).toBe('WAITING_FOR_APPROVAL');
  });

  it('should set postStatus to CONCEPT when action is not "Save"', () => {
    const createPost = new CreatePost('Post Title', 'Author', 'Content', 'Draft', 'Category');

    expect(createPost.postStatus).toBe('CONCEPT');
  });
});

