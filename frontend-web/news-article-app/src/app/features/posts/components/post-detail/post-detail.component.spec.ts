import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PostDetailComponent } from './post-detail.component';
import { PostService } from '../../services/PostService/post.service';
import { AuthenticationService } from '../../../../core/services/AuthService/authentication.service';
import { Router } from '@angular/router';
import { Post } from '../../../../core/models/posts/Post';
import { User } from '../../../../core/models/user/User';
import { NgIf } from '@angular/common';
import { CommentPageComponent } from '../../../comments/pages/comment-page/comment-page.component';
import { PostDetailPageComponent } from "../../pages/post-detail-page/post-detail-page.component";

describe('PostDetailComponent', () => {
  let component: PostDetailComponent;
  let fixture: ComponentFixture<PostDetailComponent>;
  let postService: jasmine.SpyObj<PostService>;
  let authService: jasmine.SpyObj<AuthenticationService>;
  let router: jasmine.SpyObj<Router>;

  beforeEach(() => {
    postService = jasmine.createSpyObj('PostService', ['publishPost']);
    authService = jasmine.createSpyObj('AuthenticationService', ['getLoggedInUser']);
    router = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      imports: [
        PostDetailComponent,
        NgIf,
        CommentPageComponent,
        PostDetailPageComponent
      ],
      providers: [
        { provide: PostService, useValue: postService },
        { provide: AuthenticationService, useValue: authService },
        { provide: Router, useValue: router },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(PostDetailComponent);
    component = fixture.componentInstance;
    authService.getLoggedInUser.and.returnValue({ username: 'user', password: 'user' } as User);
    component.post = new Post(1, 'Test Post', 'Test Content', 'Author', new Date(), 'Unpublished', 'Technology');
    fixture.detectChanges();
  });

  it('should navigate to update post page when navigateToUpdate is called', () => {
    component.navigateToUpdate(1);
    expect(router.navigate).toHaveBeenCalledWith(['/posts/1/update']);
  });

  it('should call publishPost and navigate to approved posts page when publishPost is called', () => {
    const post = new Post(
      1,
      'Test Title',
      'Test Content',
      'Test Author',
      new Date(),
      'APPROVED',
      'SPORTS'
    );

    component.post = post;

    fixture.detectChanges();

    const button = fixture.nativeElement.querySelector('button');
    button.click();

    expect(postService.publishPost).toHaveBeenCalledWith(post.getId());
  });


  it('should navigate to review page when navigateToReview is called', () => {
    component.navigateToReview(1);
    expect(router.navigate).toHaveBeenCalledWith(['/posts/1/review']);
  });
});
