import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Post } from "../../../../core/models/Post";
import {CreatePost} from "../../../../core/models/CreatePost";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  BASEAPIURL: string = 'http://localhost:8081/api/posts';
  http: HttpClient = inject(HttpClient);

  constructor() {}

  public getPosts(): Observable<Post[]> {
    return this.http.get<any[]>(this.BASEAPIURL).pipe(
      map(postsData => postsData.map(postData => this.mapToPost(postData))),
      tap(post => console.log(post, " fetched")),
      catchError(this.handleError)
    );
  }

  public getPostsByPostStatus(postStatus: string) : Observable<Post[]> {
    console.log(postStatus);
    return this.getPosts().pipe(
      map(posts => posts.filter(post => post.getPostStatus() == postStatus))
    );
  }

  public createNewPost(formData: any): Observable<void> {
    const createPost = this.mapFormDataToCreatePost(formData);
    return this.http.post<void>(this.BASEAPIURL, createPost).pipe(
      catchError(this.handleError)
    );
  }

  public getPostById(postId: number): Observable<Post> {
    const url = `${this.BASEAPIURL}/${postId}`;
    return this.http.get<any>(url).pipe(
      map(postData => this.mapToPost(postData)),
      tap(post => console.log('Fetched post:', post)),
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
      postData.postStatus
    );
  }

  private mapFormDataToCreatePost(formData : any): CreatePost {
    return new CreatePost(
      formData.title,
      formData.author,
      formData.content,
      formData.action
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

