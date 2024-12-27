export class UpdatablePost {
  title: string;
  author: string;
  content: string;

  constructor(title: string, author: string, content: string) {
    this.title = title;
    this.author = author;
    this.content = content;
  }
}
