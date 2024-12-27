import {Component, inject, Input} from '@angular/core';
import {Post} from "../../../../core/models/posts/Post";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {PostService} from "../../services/PostService/post.service";
import {CommentPageComponent} from "../../../comments/pages/comment-page/comment-page.component";

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [
    NgIf,
    CommentPageComponent
  ],
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent {
  @Input() post!: Post
  serv: PostService = inject(PostService);

  constructor(private router: Router) {
  }

  navigateToUpdate(postId: number) {
    this.router.navigate([`/posts/${postId}/update`]);
  }

  publishPost(postId: number) {
    this.serv.publishPost(postId);
  }
}
