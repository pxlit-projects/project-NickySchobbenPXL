import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Post } from "../../../../core/models/posts/Post";
import {CreatePost} from "../../../../core/models/posts/CreatePost";
import {UpdatablePost} from "../../../../core/models/posts/UpdatablePost";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl: string = 'http://localhost:8081/api/posts';
  http: HttpClient = inject(HttpClient);

  constructor() {}

  public getPosts(): Observable<Post[]> {
    return this.http.get<any[]>(this.baseUrl).pipe(
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
    return this.http.post<void>(this.baseUrl, createPost).pipe(
      catchError(this.handleError)
    );
  }

  public getPostById(postId: number): Observable<Post> {
    const url = `${this.baseUrl}/${postId}`;
    return this.http.get<any>(url).pipe(
      map(postData => this.mapToPost(postData)),
      tap(post => console.log('Fetched post:', post)),
      catchError(this.handleError)
    );
  }

  public updatePostById(postId: number, updatablePost: UpdatablePost): void {
    const url = `${this.baseUrl}/${postId}/update`;
    this.http.put<void>(url, updatablePost).pipe(
      catchError(this.handleError)
    ).subscribe({
      next: () => {
        console.log('Post updated successfully');
      },
      error: (err) => {
        console.error('Error updating post:', err);
      }
    });
  }

  public publishPost(postId: number): void {
    const url = `${this.baseUrl}/${postId}/publish`;
    this.http.put<void>(url, postId).pipe(
      catchError(this.handleError)
    ).subscribe({
      next: () => {
        console.log('Post has been published successfully!');
      },
      error: (err) => {
        console.error('Error publishing the post', err);
      }
    })
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

  public mapPostToUpdatablePost(postData: Post) : UpdatablePost {
    return new UpdatablePost(
      postData.getTitle(),
      postData.getContent(),
      postData.getAuthor(),
    )
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

