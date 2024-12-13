import {Component, inject, Input, OnInit} from '@angular/core';
import {Post} from "../../../../core/models/Post";
import {PostService} from "../../services/PostService/post.service";
import {NgForOf} from "@angular/common";
import {PostItemComponent} from "../post-item/post-item.component";

@Component({
  selector: 'app-post-list',
  standalone: true,
  imports: [
    NgForOf,
    PostItemComponent
  ],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.css'
})
export class PostListComponent implements OnInit{
  @Input() filteredPostStatus: string = '';
  @Input() title: string = '';
  @Input() subtitle: string = '';
  posts!: Post[];
  serv: PostService = inject(PostService);

  ngOnInit(): void {
    if (this.filteredPostStatus === '') {
      this.getAllPosts();
    }
    else {
      this.getPostsByPostStatus();
    }
  }

  getAllPosts(): void {
    this.serv.getPosts().subscribe({
      next: (data) => {
        this.posts = data;
      }
    });
  }

  getPostsByPostStatus(): void {
    this.serv.getPostsByPostStatus(this.filteredPostStatus).subscribe({
      next: (data) => {
        this.posts = data;
      }
    });
  }
}
