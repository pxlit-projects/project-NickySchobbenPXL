import { Component } from '@angular/core';
import {PostPublishedOverviewComponentComponent} from "../../components/post-published/post-published-overview-component/post-published-overview-component.component";
import {PostListComponent} from "../../components/post-list/post-list.component";

@Component({
  selector: 'app-post-category-all-page',
  standalone: true,
  imports: [
    PostPublishedOverviewComponentComponent,
    PostListComponent
  ],
  templateUrl: './post-category-all-page.component.html',
  styleUrl: './post-category-all-page.component.css'
})
export class PostCategoryAllPageComponent {
  postCategory = '';
}
