import { Component } from '@angular/core';
import {PostListComponent} from "../../components/post-list/post-list.component";

@Component({
  selector: 'app-post-unpublished-page',
  standalone: true,
  imports: [
    PostListComponent
  ],
  templateUrl: './post-unpublished-page.component.html',
  styleUrl: './post-unpublished-page.component.css'
})
export class PostUnpublishedPageComponent {
  postStatus: string = 'WAITING_FOR_APPROVAL';
  title: string = "Unpublished posts";
  subtitle: string = "These posts are currently waiting for approval";
}
