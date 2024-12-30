export class CreatePost {
  title: string;
  author: string;
  content: string;
  postStatus: string;
  category: string;

  constructor(title: string, author: string, content: string, action: string, category: string) {
    this.title = title;
    this.author = author;
    this.content = content;
    if (action === "Save") {
      this.postStatus = "WAITING_FOR_APPROVAL"
    } else {
      this.postStatus = "CONCEPT"
    }
    this.category = category;
  }
}
