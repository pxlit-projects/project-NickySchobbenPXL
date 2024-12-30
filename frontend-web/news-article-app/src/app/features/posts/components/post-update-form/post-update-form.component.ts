import {Component, inject, Input} from '@angular/core';
import {UpdatablePost} from "../../../../core/models/posts/UpdatablePost";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgIf, Location} from "@angular/common";
import {Router} from "@angular/router";
import {PostService} from "../../services/PostService/post.service";
import {timer} from "rxjs";

@Component({
  selector: 'app-post-update-form',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './post-update-form.component.html',
  styleUrl: './post-update-form.component.css'
})
export class PostUpdateFormComponent {
  @Input() postId!: number;
  @Input() updatablePost!: UpdatablePost;
  serv: PostService = inject(PostService);
  postUpdatedMessage = '';

  constructor(private router: Router, private location: Location) {
  }

  onSubmit() {
    if (this.updatablePost) {
      this.serv.updatePostById(this.postId, this.updatablePost);
      this.postUpdatedMessage = "Post has been updated successfully! You will be redirected shortly."
      timer(3000).subscribe(() => {
        this.router.navigate([`/posts/${this.postId}`]);
      });
    } else {
      console.error('No post to update');
    }
  }

  goBack() {
    this.location.back();
  }

  isSubmitDisabled(): boolean {
    return !this.updatablePost.title || !this.updatablePost.author || !this.updatablePost.content;
  }
}
