import { Component } from '@angular/core';
import {
    PostPublishedOverviewComponentComponent
} from "../../components/post-published/post-published-overview-component/post-published-overview-component.component";

@Component({
  selector: 'app-post-category-politics-page',
  standalone: true,
    imports: [
        PostPublishedOverviewComponentComponent
    ],
  templateUrl: './post-category-politics-page.component.html',
  styleUrl: './post-category-politics-page.component.css'
})
export class PostCategoryPoliticsPageComponent {
  postCategory = 'POLITICS';
}
