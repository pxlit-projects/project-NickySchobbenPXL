import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {CreatePostReview} from "../../../../core/models/reviews/CreatePostReview";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private baseUrl: string = 'http://localhost:8084/api/review';
  http: HttpClient = inject(HttpClient);

  constructor() { }

  public createNewReviewForPost(formData: any, postId: number): Observable<void> {
    const postReview: CreatePostReview = this.mapFormDataToCreatePostReview(formData, postId);
    return this.http.post<void>(this.baseUrl, postReview).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occured: ', error.error.message);
    } else {
      console.error(`Backend returned code ${error.status},` +
        `body was: ${error.error}`);
    }
    return throwError(() => new Error(`something bad happened; please try again later.`));
  }

  private mapFormDataToCreatePostReview(formData: any, postId: number): CreatePostReview {
    return new CreatePostReview(
      postId,
      formData.reviewerName,
      formData.content,
      formData.action
    );
  }
}
