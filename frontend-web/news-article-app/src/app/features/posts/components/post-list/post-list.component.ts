import {Component, inject, Input, OnInit} from '@angular/core';
import {Post} from "../../../../core/models/posts/Post";
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
export class PostListComponent implements OnInit {
  @Input() filteredPostStatus = '';
  @Input() title = '';
  @Input() subtitle = '';
  posts!: Post[];
  serv: PostService = inject(PostService);

  ngOnInit(): void {
    this.serv.getUnpublishedPostsByPostStatus(this.filteredPostStatus).subscribe({
      next: (data) => {
        this.posts = data;
      }
    });
  }
}
