import { Component, inject } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PostService } from '../../services/PostService/post.service';

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

  myForm = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.minLength(2)]),
    author: new FormControl('', [Validators.required, Validators.minLength(2)]),
    content: new FormControl('', [Validators.required, Validators.minLength(1)]),
    action: new FormControl('Save', Validators.required)
  });

  onSubmit() {
    if (this.myForm.valid) {
      const formData = this.myForm.value;
      this.serv.createNewPost(formData).subscribe({
        error: (error: any) => {
          console.log(error);
        }
      });
    }
  }
}
