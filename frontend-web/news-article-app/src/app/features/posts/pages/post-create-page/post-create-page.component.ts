import { Component, inject } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule, Location } from '@angular/common';
import { PostService } from '../../services/PostService/post.service';
import {timer} from "rxjs";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {CreatePostForm} from "../../../../core/models/posts/CreatePostForm";

@Component({
  selector: 'app-post-create-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './post-create-page.component.html',
  styleUrls: ['./post-create-page.component.css'],
  providers: [PostService],
})
export class PostCreatePageComponent {
  serv: PostService = inject(PostService);
  submittedMessage = "";

  myForm = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.minLength(2)]),
    author: new FormControl('', [Validators.required, Validators.minLength(2)]),
    content: new FormControl('', [Validators.required, Validators.minLength(1)]),
    action: new FormControl('Save', Validators.required),
    category: new FormControl('', Validators.required),
  });

  constructor(private router: Router, private location: Location) {}


  onSubmit() {
    if (this.myForm.valid) {
      const formData: CreatePostForm = {
        title: this.myForm.value.title || '',
        author: this.myForm.value.author || '',
        content: this.myForm.value.content || '',
        action: this.myForm.value.action || '',
        category: this.myForm.value.category || ''
      };
      this.serv.createNewPost(formData).subscribe({
        next: () => {
          this.submittedMessage = "Post has been created successfully! You will be redirected shortly.";
          timer(3000).subscribe(() => {
            this.router.navigate([`/posts/unpublished`]);
          });
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        },
      });
    }
  }

  goBack() {
    this.location.back();
  }
}
