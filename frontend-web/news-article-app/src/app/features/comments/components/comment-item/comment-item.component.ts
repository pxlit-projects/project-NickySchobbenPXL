import {Component, inject, Input} from '@angular/core';
import {Comment} from "../../../../core/models/comments/Comment";
import {CommonModule} from "@angular/common";
import {CommentService} from "../../services/CommentService/comment.service";
import {FormsModule} from "@angular/forms";
import {UpdatableComment} from "../../../../core/models/comments/UpdatableComment";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-comment-item',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './comment-item.component.html',
  styleUrl: './comment-item.component.css'
})
export class CommentItemComponent {
  @Input() comment!: Comment;
  updatableComment: UpdatableComment = {description: ''};
  isDropdownOpen = false;
  editMode = false;
  serv: CommentService = inject(CommentService);

  public toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  public deleteComment(): void {
    this.serv.deleteComment(this.comment.commentId).subscribe({
      next: () => console.log('Comment deleted successfully.'),
      error: (err) => console.error('Error deleting comment:', err),
    });
  }

  public toggleEditMode(): void {
    this.editMode = true;
    this.isDropdownOpen = false;
  }

  public updateComment(): void {
    this.updatableComment.description = this.comment.description;
    this.serv.updateComment(this.comment.commentId, this.updatableComment).subscribe({
        next: () => console.log('Comment updated successfully.'),
        error: (err: HttpErrorResponse) => console.error('Error updating comment:', err),
    });
    this.editMode = false;
  }

  public leaveEditMode(): void {
    this.editMode = false;
  }
}
