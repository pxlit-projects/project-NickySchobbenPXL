import {Component, Input} from '@angular/core';
import {PostPublishedItemBigComponent} from "../post-published-item-big/post-published-item-big.component";
import {PostPublishedItemSmallComponent} from "../post-published-item-small/post-published-item-small.component";
import {PostPublishedListComponent} from "../post-published-list/post-published-list.component";
import {PostFilterComponent} from "../../post-filter/post-filter.component";
import {PostFilter} from "../../../../../core/models/filter/PostFilter";

@Component({
  selector: 'app-post-published-overview-component',
  standalone: true,
  imports: [
    PostPublishedItemBigComponent,
    PostPublishedItemSmallComponent,
    PostPublishedListComponent,
    PostFilterComponent
  ],
  templateUrl: './post-published-overview-component.component.html',
  styleUrl: './post-published-overview-component.component.css'
})
export class PostPublishedOverviewComponentComponent {
  @Input() filteredPostCategory = '';
  filterAuthorOrContent: PostFilter = new PostFilter('', '');

  catchFilter(filter: PostFilter):void {
    console.log("Step 2: post-published-overview is working");
    this.filterAuthorOrContent = { ...filter};
  }
}
