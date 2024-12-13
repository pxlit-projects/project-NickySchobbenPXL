import {Component} from '@angular/core';
import {PostService} from "../../services/PostService/post.service";
import {CommonModule} from "@angular/common";
import {PostListComponent} from "../../components/post-list/post-list.component";

@Component({
  selector: 'app-post-overview-page',
  standalone: true,
  imports: [CommonModule, PostListComponent
  ],
  templateUrl: './post-overview-page.component.html',
  styleUrl: './post-overview-page.component.css',
  providers: [PostService],
})
export class PostOverviewPageComponent {
  postStatus: string = 'APPROVED';
  title: string = "Approved posts";
  subtitle: string = "These posts have been approved and published";
}
