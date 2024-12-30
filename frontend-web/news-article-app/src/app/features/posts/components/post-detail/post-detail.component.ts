import {Component, inject, Input, OnInit} from '@angular/core';
import {Post} from "../../../../core/models/posts/Post";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {PostService} from "../../services/PostService/post.service";
import {CommentPageComponent} from "../../../comments/pages/comment-page/comment-page.component";
import {User} from "../../../../core/models/user/User";
import {AuthenticationService} from "../../../../core/services/AuthService/authentication.service";
import {timer} from "rxjs";

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
export class PostDetailComponent implements OnInit{
  @Input() post!: Post
  serv: PostService = inject(PostService);
  user: User | null = null;

  constructor(private authService: AuthenticationService, private router: Router) {
  }

  ngOnInit(): void {
    this.user = this.authService.getLoggedInUser();
  }

  navigateToUpdate(postId: number) {
    this.router.navigate([`/posts/${postId}/update`]);
  }

  publishPost(postId: number) {
    this.serv.publishPost(postId);
    timer(1000).subscribe(() => {
      this.router.navigate([`/posts/approved`]);
    });
  }

  navigateToReview(postId: number) {
    this.router.navigate([`/posts/${postId}/review`]);
  }
}
