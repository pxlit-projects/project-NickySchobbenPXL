import {Component, Input} from '@angular/core';
import {CommentListComponent} from "../comment-list/comment-list.component";
import {CommentInputComponent} from "../comment-input/comment-input.component";

@Component({
  selector: 'app-comment-section-container',
  standalone: true,
  imports: [
    CommentListComponent,
    CommentInputComponent
  ],
  templateUrl: './comment-section-container.component.html',
  styleUrl: './comment-section-container.component.css'
})
export class CommentSectionContainerComponent {
  @Input() postId!: number;
}
