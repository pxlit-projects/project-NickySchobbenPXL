import {Component, inject, Input} from '@angular/core';
import {UpdatablePost} from "../../../../core/models/posts/UpdatablePost";
import {FormsModule} from "@angular/forms";
import {NgIf, Location} from "@angular/common";
import {Router} from "@angular/router";
import {PostService} from "../../services/PostService/post.service";

@Component({
  selector: 'app-post-update-form',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './post-update-form.component.html',
  styleUrl: './post-update-form.component.css'
})
export class PostUpdateFormComponent {
  @Input() postId!: number;
  @Input() updatablePost!: UpdatablePost;
  serv: PostService = inject(PostService);

  constructor(private router: Router, private location: Location) {
  }

  onSubmit() {
    if (this.updatablePost) {
      this.serv.updatePostById(this.postId, this.updatablePost);
    } else {
      console.error('No post to update');
    }
    this.router.navigate([`/posts/${this.postId}`]);
  }

  goBack() {
    this.location.back();
  }
}
