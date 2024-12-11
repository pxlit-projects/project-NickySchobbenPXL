export class Post {
  private id: number;
  private title: string;
  private content: string;
  private author: string;
  private date: Date;
  private postStatus: string;

  constructor(id: number, title: string, content: string, author: string, date: Date, postStatus: string) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
    this.date = date;
    this.postStatus = postStatus;
  }

  getId(): number {
    return this.id;
  }

  getTitle(): string {
    return this.title;
  }

  getContent(): string {
    return this.content;
  }

  getAuthor(): string {
    return this.author;
  }

  getDate(): Date {
    return this.date;
  }

  getPostStatus(): string {
    return this.postStatus;
  }
}
