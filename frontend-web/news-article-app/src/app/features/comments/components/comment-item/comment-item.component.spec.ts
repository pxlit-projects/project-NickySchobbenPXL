import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CommentItemComponent } from './comment-item.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {CommentService} from "../../services/CommentService/comment.service";
import { of, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Comment } from '../../../../core/models/comments/Comment';
import { UpdatableComment } from '../../../../core/models/comments/UpdatableComment';

describe('CommentItemComponent', () => {
  let component: CommentItemComponent;
  let fixture: ComponentFixture<CommentItemComponent>;
  let commentService: jasmine.SpyObj<CommentService>;

  const mockComment: Comment = {
    commentId: 1,
    postId: 1,
    commenter: 'John Doe',
    description: 'This is a comment.',
    dateOfComment: new Date(),
  };

  beforeEach(() => {
    commentService = jasmine.createSpyObj('CommentService', ['deleteComment', 'updateComment']);

    TestBed.configureTestingModule({
      imports: [CommentItemComponent, CommonModule, FormsModule], // Import the standalone component here
      providers: [
        { provide: CommentService, useValue: commentService },
      ]
    });

    fixture = TestBed.createComponent(CommentItemComponent);
    component = fixture.componentInstance;
    component.comment = mockComment;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should toggle dropdown open/close', () => {
    expect(component.isDropdownOpen).toBeFalse();

    component.toggleDropdown();
    expect(component.isDropdownOpen).toBeTrue();

    component.toggleDropdown();
    expect(component.isDropdownOpen).toBeFalse();
  });

  it('should set editMode to true and close dropdown when toggling edit mode', () => {
    component.toggleEditMode();

    expect(component.editMode).toBeTrue();
    expect(component.isDropdownOpen).toBeFalse();
  });

  it('should set editMode to false when leaving edit mode', () => {
    component.toggleEditMode();
    component.leaveEditMode();

    expect(component.editMode).toBeFalse();
  });

  it('should call CommentService.deleteComment when deleting a comment', () => {
    commentService.deleteComment.and.returnValue(of(undefined));

    component.deleteComment();

    expect(commentService.deleteComment).toHaveBeenCalledOnceWith(mockComment.commentId);
  });

  it('should handle error when deleting a comment', () => {
    const errorResponse = new HttpErrorResponse({ error: 'Error', status: 500 });
    commentService.deleteComment.and.returnValue(throwError(() => errorResponse));

    spyOn(console, 'error'); // Spy on console.error to check if it's called
    component.deleteComment();

    expect(console.error).toHaveBeenCalledWith('Error deleting comment:', errorResponse);
  });

  it('should call CommentService.updateComment when updating a comment', () => {
    const updatableComment: UpdatableComment = { description: 'Updated comment' };
    component.updatableComment = updatableComment;
    commentService.updateComment.and.returnValue(of(mockComment));

    component.updateComment();

    expect(commentService.updateComment).toHaveBeenCalledOnceWith(mockComment.commentId, updatableComment);
  });

  it('should handle error when updating a comment', () => {
    const errorResponse = new HttpErrorResponse({ error: 'Error', status: 500 });
    commentService.updateComment.and.returnValue(throwError(() => errorResponse));

    spyOn(console, 'error'); // Spy on console.error to check if it's called
    component.updateComment();

    expect(console.error).toHaveBeenCalledWith('Error updating comment:', errorResponse);
  });
});
