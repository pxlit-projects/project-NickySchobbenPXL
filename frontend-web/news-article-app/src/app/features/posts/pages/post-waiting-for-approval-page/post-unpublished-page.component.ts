import { Component } from '@angular/core';
import {PostListComponent} from "../../components/post-list/post-list.component";

@Component({
  selector: 'app-post-waiting-for-approval-page',
  standalone: true,
  imports: [
    PostListComponent
  ],
  templateUrl: './post-unpublished-page.component.html',
  styleUrl: './post-unpublished-page.component.css'
})
export class PostUnpublishedPageComponent {
  postStatus = 'WAITING_FOR_APPROVAL';
  title = "Unpublished posts";
  subtitle = "These posts are currently waiting for approval";
}
