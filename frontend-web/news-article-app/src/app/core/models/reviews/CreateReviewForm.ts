export class CreateReviewForm {
  constructor(
    public postId: number,
    public reviewerName: string,
    public content: string,
    public action: string,
  ) {}
}
