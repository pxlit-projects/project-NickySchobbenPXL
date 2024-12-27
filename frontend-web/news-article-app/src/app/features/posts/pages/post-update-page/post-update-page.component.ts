import {Component, inject, OnInit} from '@angular/core';
import {PostUpdateFormComponent} from "../../components/post-update-form/post-update-form.component";
import {UpdatablePost} from "../../../../core/models/posts/UpdatablePost";
import {PostService} from "../../services/PostService/post.service";
import {ActivatedRoute} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-post-update-page',
  standalone: true,
  imports: [
    PostUpdateFormComponent,
    NgIf
  ],
  templateUrl: './post-update-page.component.html',
  styleUrl: './post-update-page.component.css'
})
export class PostUpdatePageComponent implements OnInit{
  route: ActivatedRoute = inject(ActivatedRoute);
  serv: PostService = inject(PostService);
  updatablePost!: UpdatablePost;
  postId: number = this.route.snapshot.params['id'];

  ngOnInit(): void {
    this.serv.getPostById(this.postId).subscribe({
      next: (post) => {
        this.updatablePost = this.serv.mapPostToUpdatablePost(post);
      }
    });
  }
}
