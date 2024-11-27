import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Post } from "../../../../core/models/Post";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  BASEAPIURL: string = 'http://localhost:8081/api/post';
  http: HttpClient = inject(HttpClient);

  constructor() {}

  public getPosts(): Observable<Post[]> {
    return this.http.get<any[]>(this.BASEAPIURL).pipe(
      map(postsData => postsData.map(postData => this.mapToPost(postData))),
      tap(post => console.log(post, " fetched")),
      catchError(this.handleError)
    );
  }

  private mapToPost(postData: any): Post {
    return new Post(
      postData.id,
      postData.title,
      postData.content,
      postData.author,
      new Date(postData.date),
      postData.published
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
}

