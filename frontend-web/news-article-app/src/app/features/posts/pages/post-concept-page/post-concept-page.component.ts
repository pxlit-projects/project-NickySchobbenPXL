import { Component } from '@angular/core';
import {PostListComponent} from "../../components/post-list/post-list.component";

@Component({
  selector: 'app-post-concept-page',
  standalone: true,
  imports: [
    PostListComponent
  ],
  templateUrl: './post-concept-page.component.html',
  styleUrl: './post-concept-page.component.css'
})
export class PostConceptPageComponent {
  postStatus: string = 'CONCEPT';
  title: string = "Concept posts";
  subtitle: string = "These posts have been saved as a concept.";
}