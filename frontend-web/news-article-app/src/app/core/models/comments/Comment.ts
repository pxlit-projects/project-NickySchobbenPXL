export class Comment {
  commentId: number;
  postId: number;
  description: string;
  commenter: string;
  dateOfComment: Date;

  constructor(commentId: number, postId: number, description: string, commenter: string, dateOfComment: Date) {
    this.commentId = commentId;
    this.postId = postId;
    this.description = description;
    this.commenter = commenter;
    this.dateOfComment = dateOfComment;
  }
}
