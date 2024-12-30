import {Component, inject, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {PostPublishedItemBigComponent} from "../post-published-item-big/post-published-item-big.component";
import {PostPublishedItemSmallComponent} from "../post-published-item-small/post-published-item-small.component";
import {Post} from "../../../../../core/models/posts/Post";
import {PostService} from "../../../services/PostService/post.service";
import {CommonModule} from "@angular/common";
import {PostFilter} from "../../../../../core/models/filter/PostFilter";

@Component({
  selector: 'app-post-published-list',
  standalone: true,
    imports: [
        PostPublishedItemBigComponent,
        PostPublishedItemSmallComponent,
        CommonModule
    ],
  templateUrl: './post-published-list.component.html',
  styleUrl: './post-published-list.component.css'
})
export class PostPublishedListComponent implements OnInit, OnChanges{
  @Input() filteredPostCategory = '';
  @Input() filterAuthorOrContent: PostFilter = new PostFilter('', '');
  posts!: Post[];
  filteredPosts!: Post[];
  serv: PostService = inject(PostService);

  ngOnInit(): void {
    this.getPostsByPostStatus();
  }

  getPostsByPostStatus(): void {
    this.serv.getPublishedPostsByCategory(this.filteredPostCategory).subscribe({
      next: (data) => {
        this.posts = data;
        this.filteredPosts = data;
      }
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log("Step 3: post-list is working");
    if (changes['filterAuthorOrContent']) {
      this.filterPosts();
    }
  }

  filterPosts(): void {
    if (this.filterAuthorOrContent.value == "") {
      return;
    }
    if (this.filterAuthorOrContent.field == "author") {
      this.filterByAuthor(this.filterAuthorOrContent.value);
    }
    if (this.filterAuthorOrContent.field == "content") {
      this.filterByContent(this.filterAuthorOrContent.value);
    }
  }

  filterByAuthor(value: string):void {
    this.filteredPosts = this.posts.filter(post =>
      post.getAuthor().toLowerCase().includes(value.toLowerCase())
    );
  }

  filterByContent(value: string):void {
    this.filteredPosts = this.posts.filter(post =>
      post.getContent().toLowerCase().includes(value.toLowerCase())
    );
  }
}
