import { Component } from '@angular/core';
import {
  PostPublishedOverviewComponentComponent
} from "../../components/post-published/post-published-overview-component/post-published-overview-component.component";

@Component({
  selector: 'app-post-category-science-page',
  standalone: true,
  imports: [
    PostPublishedOverviewComponentComponent
  ],
  templateUrl: './post-category-science-page.component.html',
  styleUrl: './post-category-science-page.component.css'
})
export class PostCategorySciencePageComponent {
  postCategory = 'SCIENCE';
}
