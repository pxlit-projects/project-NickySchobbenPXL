import { Component, Input, OnInit } from '@angular/core';
import { Comment } from "../../../../core/models/comments/Comment";
import { CommentService } from "../../services/CommentService/comment.service";
import {CommentItemComponent} from "../comment-item/comment-item.component";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  standalone: true,
  imports: [
    CommentItemComponent,
    CommonModule
  ],
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {
  @Input() postId!: number;
  comments: Comment[] = [];

  constructor(private commentService: CommentService) {}

  ngOnInit(): void {
    this.commentService.getCommentsForPost(this.postId);
    this.commentService.comments$.subscribe({
      next: (comments: Comment[]) => {
        this.comments = comments;
      },
      error: (error) => {
        console.error('Error fetching comments:', error);
      }
    });
  }
}
