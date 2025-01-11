import { Component, inject, Input, OnInit } from '@angular/core';
import { CreateComment } from '../../../../core/models/comments/CreateComment';
import { CommentService } from '../../services/CommentService/comment.service';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../../../core/services/AuthService/authentication.service';
import { User } from '../../../../core/models/user/User';

@Component({
  selector: 'app-comment-input',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './comment-input.component.html',
  styleUrls: ['./comment-input.component.css'],
})
export class CommentInputComponent implements OnInit {
  @Input() postId!: number;
  private serv = inject(CommentService);
  private authServ = inject(AuthenticationService);
  user: User | null = null;
  newComment: CreateComment = new CreateComment('', '');

  ngOnInit(): void {
    this.authServ.getLoggedInUserObservable().subscribe({
      next: (user) => {
        if (user) {
          this.user = user; // Store user data
          this.newComment.commenter = user.username;
        }
      },
      error: (err) => console.error('Error fetching logged-in user:', err),
    });
  }

  createNewComment(): void {
    if (this.newComment.description?.trim()) {
      this.serv.createNewComment(this.postId, this.newComment).subscribe({
        next: (createdComment) => {
          console.log('Comment added:', createdComment);
          this.newComment.description = '';
        },
        error: (error) => {
          console.error('Error adding comment:', error);
        },
      });
    } else {
      console.warn('Comment description is empty.');
    }
  }
}
