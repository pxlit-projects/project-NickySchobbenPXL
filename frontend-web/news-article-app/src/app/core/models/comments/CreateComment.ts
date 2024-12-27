export class CreateComment {
  description: string;
  commenter: string;

  constructor(description: string, commenter: string) {
    this.description = description;
    this.commenter = commenter;
  }
}
