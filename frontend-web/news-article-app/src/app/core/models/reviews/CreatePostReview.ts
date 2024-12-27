export class CreatePostReview {
  postId: number;
  reviewerName: string;
  reviewContent: string;
  reviewStatus: string;

  constructor(postId: number, reviewerName: string, reviewContent: string, action: string) {
    this.postId = postId;
    this.reviewerName = reviewerName;
    this.reviewContent = reviewContent;
    if (action === "Approve") {
      this.reviewStatus = "APPROVED"
    } else {
      this.reviewStatus = "DENIED"
    }
  }
}
