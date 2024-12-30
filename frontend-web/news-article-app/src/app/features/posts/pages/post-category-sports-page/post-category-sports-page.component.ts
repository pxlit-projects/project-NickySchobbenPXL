import { Component } from '@angular/core';
import {
  PostPublishedOverviewComponentComponent
} from "../../components/post-published/post-published-overview-component/post-published-overview-component.component";

@Component({
  selector: 'app-post-category-sports-page',
  standalone: true,
  imports: [
    PostPublishedOverviewComponentComponent
  ],
  templateUrl: './post-category-sports-page.component.html',
  styleUrl: './post-category-sports-page.component.css'
})
export class PostCategorySportsPageComponent {
  postCategory = 'SPORTS';
}
