import {Component, inject, Input, OnInit} from '@angular/core';
import {CreateComment} from "../../../../core/models/comments/CreateComment";
import {CommentService} from "../../services/CommentService/comment.service";
import {FormsModule} from "@angular/forms";
import {AuthenticationService} from "../../../../core/services/AuthService/authentication.service";
import {User} from "../../../../core/models/user/User";

@Component({
  selector: 'app-comment-input',
  standalone: true,
  imports: [
    FormsModule,
  ],
  templateUrl: './comment-input.component.html',
  styleUrl: './comment-input.component.css'
})
export class CommentInputComponent implements OnInit{
  @Input() postId!: number;
  serv: CommentService = inject(CommentService);
  authServ: AuthenticationService = inject(AuthenticationService);
  user: User | null = this.authServ.getLoggedInUser();
  newComment: CreateComment = new CreateComment("", "");

  ngOnInit(): void {
    this.authServ.getLoggedInUserObservable().subscribe(user => {
      if (user) {
        this.newComment.commenter = user.username;  // Access the username of the logged-in user
      }
    });
  }

  public createNewComment(): void {
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
