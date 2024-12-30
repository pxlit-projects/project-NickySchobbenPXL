export class Post {
  readonly id: number;
  readonly title: string;
  readonly content: string;
  readonly author: string;
  readonly date: Date;
  readonly postStatus: string;
  readonly category: string;

  constructor(id: number, title: string, content: string, author: string, date: Date, postStatus: string, category: string) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
    this.date = date;
    this.postStatus = postStatus;
    this.category = category;
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

  getDate(): string {
    return new Intl.DateTimeFormat('en-GB').format(this.date);
  }

  getPostStatus(): string {
    return this.postStatus;
  }

  getCategory(): string {
    return this.category;
  }
}
