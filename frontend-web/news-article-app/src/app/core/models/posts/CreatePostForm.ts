export class CreatePostForm {
  constructor(
    public title: string,
    public author: string,
    public content: string,
    public action: string,
    public category: string
  ) {}
}
