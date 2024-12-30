import { Component, inject, Input } from '@angular/core';
import { ReviewService } from "../../services/ReviewService/review.service";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import { NgIf, Location } from "@angular/common";
import { timer } from 'rxjs';
import {HttpErrorResponse} from "@angular/common/http";
import {CreateReviewForm} from "../../../../core/models/reviews/CreateReviewForm";

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent {
  @Input() postId!: number;
  serv: ReviewService = inject(ReviewService);
  submittedMessage = "";

  myForm = new FormGroup({
    reviewerName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    content: new FormControl(''),
    action: new FormControl('Approve', Validators.required)
  });

  constructor(private location: Location) {
    this.myForm.get('action')?.valueChanges.subscribe((value) => {
      const contentControl = this.myForm.get('content');
      if (value === 'Deny') {
        contentControl?.setValidators([Validators.required, Validators.minLength(2)]);
      } else {
        contentControl?.clearValidators();
      }
      contentControl?.updateValueAndValidity();
    });
  }

  onSubmit() {
    if (this.myForm.valid) {
      const formData: CreateReviewForm = {
        postId: this.postId,
        reviewerName: this.myForm.value.reviewerName || '',
        content: this.myForm.value.content || '',
        action: this.myForm.value.action || '',
      };
      this.serv.createNewReviewForPost(formData).subscribe({
        error: (error: HttpErrorResponse) => {
          console.error(error);
        }
      });
      this.submittedMessage = "Review has been submitted successfully! You will be redirected shortly."
      timer(3000).subscribe(() => {
        this.goBack();
      });
    }
  }

  goBack() {
    this.location.back();
  }
}

