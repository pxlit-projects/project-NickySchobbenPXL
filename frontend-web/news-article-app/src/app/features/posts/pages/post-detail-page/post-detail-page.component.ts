import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostDetailComponent} from "../../components/post-detail/post-detail.component";
import {PostService} from "../../services/PostService/post.service";
import {Post} from "../../../../core/models/Post";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-post-detail-page',
  standalone: true,
  imports: [
    PostDetailComponent,
  ],
  templateUrl: './post-detail-page.component.html',
  styleUrl: './post-detail-page.component.css'
})
export class PostDetailPageComponent implements OnInit{
  route: ActivatedRoute = inject(ActivatedRoute);
  postId: number = this.route.snapshot.params['id'];
  serv: PostService = inject(PostService);
  post!: Post;

  ngOnInit(): void {
    this.serv.getPostById(this.postId).subscribe({
      next: (data) => {
        this.post = data;
      }
    });
  }
}
