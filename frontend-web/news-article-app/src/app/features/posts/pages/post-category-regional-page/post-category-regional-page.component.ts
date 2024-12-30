import { Component } from '@angular/core';
import {
    PostPublishedOverviewComponentComponent
} from "../../components/post-published/post-published-overview-component/post-published-overview-component.component";

@Component({
  selector: 'app-post-category-regional-page',
  standalone: true,
    imports: [
        PostPublishedOverviewComponentComponent
    ],
  templateUrl: './post-category-regional-page.component.html',
  styleUrl: './post-category-regional-page.component.css'
})
export class PostCategoryRegionalPageComponent {
  postCategory = 'REGIONAL';
}
