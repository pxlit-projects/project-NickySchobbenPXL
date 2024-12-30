import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PostDetailComponent} from "../../components/post-detail/post-detail.component";
import {PostService} from "../../services/PostService/post.service";
import {Post} from "../../../../core/models/posts/Post";
import {CommentPageComponent} from "../../../comments/pages/comment-page/comment-page.component";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-post-detail-page',
  standalone: true,
  imports: [
    PostDetailComponent,
    CommentPageComponent,
    CommonModule
  ],
  templateUrl: './post-detail-page.component.html',
  styleUrl: './post-detail-page.component.css'
})
export class PostDetailPageComponent implements OnInit{
  route: ActivatedRoute = inject(ActivatedRoute);
  postId: number = this.route.snapshot.params['id'];
  serv: PostService = inject(PostService);
  post!: Post;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.serv.getPostById(this.postId).subscribe({
      next: (data) => {
        this.post = data;
      },
      error: (err) => {
        console.error('Error fetching post:', err);
        this.router.navigate(['/notfound']);
      }
    });
  }

  navigateToReview(postId: number) {
    this.router.navigate([`/posts/${postId}/review`]);
  }
}
