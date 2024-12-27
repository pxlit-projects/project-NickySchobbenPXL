import {Component, inject, Input} from '@angular/core';
import {CreateComment} from "../../../../core/models/comments/CreateComment";
import {CommentService} from "../../services/CommentService/comment.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-comment-input',
  standalone: true,
  imports: [
    FormsModule,
  ],
  templateUrl: './comment-input.component.html',
  styleUrl: './comment-input.component.css'
})
export class CommentInputComponent {
  @Input() postId!: number;
  serv: CommentService = inject(CommentService);
  newComment: CreateComment = new CreateComment("", "HardCodedUser");

  createNewComment() {
    if (this.newComment.description) {
      this.serv.createNewComment(this.postId, this.newComment).subscribe({
        next: (createdComment) => {
          console.log('Comment added:', createdComment);
        },
        error: (error) => {
          console.error('Error adding comment:', error);
        }
      });
      this.newComment.description = "";
    }
  }
}
