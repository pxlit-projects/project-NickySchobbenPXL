import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CommentInputComponent } from './comment-input.component'; // Standalone component
import { CommentService } from '../../services/CommentService/comment.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { AuthenticationService } from '../../../../core/services/AuthService/authentication.service';
import { User } from '../../../../core/models/user/User';
import {CreateComment} from "../../../../core/models/comments/CreateComment";
import {Comment} from "../../../../core/models/comments/Comment";

describe('CommentInputComponent', () => {
  let component: CommentInputComponent;
  let fixture: ComponentFixture<CommentInputComponent>;
  let commentService: CommentService;
  let authService: AuthenticationService;
  let mockUser: User;
  let mockCreateComment: CreateComment;

  beforeEach(() => {
    mockUser = { username: 'user3', password: 'user3' } as User; // Mock user data
    mockCreateComment = new CreateComment('This is a new comment', 'user3'); // Mock new comment

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, FormsModule, CommentInputComponent], // Add CommentInputComponent to imports array
      providers: [
        CommentService,
        {
          provide: AuthenticationService,
          useValue: {
            getLoggedInUserObservable: () => of(mockUser), // Mock user observable
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(CommentInputComponent);
    component = fixture.componentInstance;
    commentService = TestBed.inject(CommentService);
    authService = TestBed.inject(AuthenticationService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with logged-in user and set commenter', () => {
    component.ngOnInit();
    expect(component.user).toEqual(mockUser);
    expect(component.newComment.commenter).toBe(mockUser.username);
  });

  it('should call createNewComment method from CommentService', () => {
    const mockResponse: Comment = {
      commentId: 1,
      postId: 123,
      description: 'This is a new comment',
      commenter: 'user3',
      dateOfComment: new Date(),
    };

    const createNewCommentSpy = spyOn(commentService, 'createNewComment').and.returnValue(of(mockResponse));

    component.postId = 123;
    component.newComment = new CreateComment('This is a new comment', 'user3');

    component.createNewComment(); // Simulate the actual method call

    expect(createNewCommentSpy).toHaveBeenCalledWith(component.postId, component.newComment);
  });


  it('should reset newComment description after successful comment creation', () => {
    const mockResponse: Comment = {
      commentId: 1,
      postId: component.postId,
      description: mockCreateComment.description,
      commenter: mockCreateComment.commenter,
      dateOfComment: new Date(),
    };

    spyOn(commentService, 'createNewComment').and.returnValue(of(mockResponse));

    component.postId = 123;
    component.createNewComment();

    expect(component.newComment.description).toBe('');
  });

  it('should not call createNewComment when description is empty', () => {
    component.newComment.description = '';

    const createNewCommentSpy = spyOn(commentService, 'createNewComment');
    component.createNewComment();

    expect(createNewCommentSpy).not.toHaveBeenCalled();
  });

  it('should display a warning if description is empty', () => {
    component.newComment.description = '';

    const consoleWarnSpy = spyOn(console, 'warn');

    component.createNewComment();

    expect(consoleWarnSpy).toHaveBeenCalledWith('Comment description is empty.');
  });

  it('should call createNewComment when form is submitted', () => {
    component.postId = 123;
    const mockResponse: Comment = {
      commentId: 1,
      postId: component.postId,
      description: mockCreateComment.description,
      commenter: mockCreateComment.commenter,
      dateOfComment: new Date(),
    };

    const createNewCommentSpy = spyOn(commentService, 'createNewComment').and.returnValue(of(mockResponse));

    component.newComment.description = 'This is a valid comment description';
    component.createNewComment();

    expect(createNewCommentSpy).toHaveBeenCalled();
  });
});
