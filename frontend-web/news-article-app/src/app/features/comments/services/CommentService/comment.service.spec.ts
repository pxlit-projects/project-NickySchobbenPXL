import { TestBed } from '@angular/core/testing';
import { CommentService } from './comment.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Comment } from '../../../../core/models/comments/Comment';
import { CreateComment } from '../../../../core/models/comments/CreateComment';
import { UpdatableComment } from "../../../../core/models/comments/UpdatableComment";

describe('CommentService', () => {
  let service: CommentService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CommentService],
    });
    service = TestBed.inject(CommentService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch comments for a post and update the commentsSubject', () => {
    const mockComments: Comment[] = [
      new Comment(1, 1, 'First description', 'Test', new Date()),
      new Comment(2, 1, 'Second comment', 'Tester', new Date()),
    ];

    service.getCommentsForPost(1);

    const req = httpMock.expectOne('http://localhost:8082/api/comment/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockComments);

    service.comments$.subscribe(comments => {
      expect(comments.length).toBe(2);
      expect(comments[0].commentId).toBe(1);
      expect(comments[1].commentId).toBe(2);
    });
  });

  it('should create a new comment and add it to the commentsSubject', () => {
    const mockCreatedComment = new Comment(3, 1, 'New comment', 'Tester', new Date());
    const mockRequest = new CreateComment('New comment', 'Tester');

    service.createNewComment(1, mockRequest).subscribe(comment => {
      expect(comment.commentId).toBe(3);
      expect(comment.description).toBe('New comment');
    });

    const req = httpMock.expectOne('http://localhost:8082/api/comment/1');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockRequest);
    req.flush(mockCreatedComment);
  });

  it('should delete a comment and remove it from the commentsSubject', () => {
    const initialComments: Comment[] = [
      new Comment(1, 1, 'First comment', 'Tester', new Date()),
      new Comment(2, 1, 'Second comment', 'Tester', new Date()),
    ];

    service['commentsSubject'].next(initialComments);

    service.deleteComment(1).subscribe(() => {
      service.comments$.subscribe(comments => {
        expect(comments.length).toBe(1);
        expect(comments[0].commentId).toBe(2);
      });
    });

    const req = httpMock.expectOne('http://localhost:8082/api/comment/1');
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });

  it('should update a comment and reflect it in the commentsSubject', () => {
    const initialComments: Comment[] = [
      new Comment(1, 1, 'First comment', 'Tester', new Date()),
      new Comment(2, 1, 'Second comment', 'Tester', new Date()),
    ];
    const updatedComment = new UpdatableComment('Updated comment');
    const updatedCommentResponse = new Comment(1, 1, 'Updated comment', 'Tester', new Date());

    service['commentsSubject'].next(initialComments);

    service.updateComment(1, updatedComment).subscribe(comment => {
      expect(comment.description).toBe('Updated comment');
    });

    const req = httpMock.expectOne('http://localhost:8082/api/comment/1');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedComment);
    req.flush(updatedCommentResponse);
  });
});
