export class UpdatablePost {
  title: string;
  author: string;
  content: string;
  category: string;

  constructor(title: string, author: string, content: string, category: string) {
    this.title = title;
    this.author = author;
    this.content = content;
    this.category = category;
  }
}
