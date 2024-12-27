import {Component, Input} from '@angular/core';
import {
  CommentSectionContainerComponent
} from "../../components/comment-section-container/comment-section-container.component";

@Component({
  selector: 'app-comment-page',
  standalone: true,
  imports: [
    CommentSectionContainerComponent
  ],
  templateUrl: './comment-page.component.html',
  styleUrl: './comment-page.component.css'
})
export class CommentPageComponent {
  @Input() postId!: number;
}
