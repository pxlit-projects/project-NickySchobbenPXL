import {inject, Injectable} from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { Comment } from '../../../../core/models/comments/Comment'
import { CreateComment } from '../../../../core/models/comments/CreateComment'
import {UpdatableComment} from "../../../../core/models/comments/UpdatableComment";

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private baseUrl = 'http://localhost:8082/api/comment';
  http: HttpClient = inject(HttpClient);
  private commentsSubject = new BehaviorSubject<Comment[]>([]);
  comments$ = this.commentsSubject.asObservable();


  public getCommentsForPost(postId: number): void {
    const url = `${this.baseUrl}/${postId}`;
    this.http.get<Comment[]>(url).pipe(
      tap((comments: Comment[]) => {
        this.commentsSubject.next(comments);
      }),
      catchError(this.handleError)
    ).subscribe();
  }

  public createNewComment(postId: number, comment: CreateComment): Observable<Comment> {
    const url = `${this.baseUrl}/${postId}`;
    return this.http.post<Comment>(url, comment).pipe(
      tap((createdComment) => {
        const currentComments = this.commentsSubject.getValue();
        this.commentsSubject.next([...currentComments, createdComment]);
      }),
      catchError(this.handleError)
    );
  }

  public deleteComment(commentId: number): Observable<void> {
    const url = `${this.baseUrl}/${commentId}`;
    return this.http.delete<void>(url).pipe(
      tap(() => {
        const currentComments = this.commentsSubject.getValue();
        const updatedComments = currentComments.filter(comment => comment.commentId !== commentId);
        this.commentsSubject.next(updatedComments);
      }),
      catchError(this.handleError)
    );
  }

  public updateComment(commentId: number, updatedComment: UpdatableComment): Observable<Comment> {
    const url = `${this.baseUrl}/${commentId}`;
    return this.http.put<Comment>(url, updatedComment).pipe(
      tap((updated: Comment) => {
        const currentComments = this.commentsSubject.getValue();
        const updatedComments = currentComments.map(comment =>
          comment.commentId === commentId ? updated : comment
        );
        this.commentsSubject.next(updatedComments);
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error(error);
    throw new Error('Error occurred');
  }
}

