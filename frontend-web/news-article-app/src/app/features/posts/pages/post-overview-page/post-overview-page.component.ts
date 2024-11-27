import {Component, inject, OnInit} from '@angular/core';
import {Post} from "../../../../core/models/Post";
import {PostService} from "../../services/PostService/post.service";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-post-overview-page',
  standalone: true,
  imports: [CommonModule
  ],
  templateUrl: './post-overview-page.component.html',
  styleUrl: './post-overview-page.component.css'
})
export class PostOverviewPageComponent implements OnInit {
  postList: Post[] = [];
  serv: PostService = inject(PostService);

  ngOnInit() {
    this.serv.getPosts().subscribe({
      next: (data) => {
        this.postList = data;
      }
    });
  }
}
