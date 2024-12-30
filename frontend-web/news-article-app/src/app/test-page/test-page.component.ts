import { Component } from '@angular/core';
import {PostUpdatePageComponent} from "../features/posts/pages/post-update-page/post-update-page.component";
import {CommentInputComponent} from "../features/comments/components/comment-input/comment-input.component";
import {CommentListComponent} from "../features/comments/components/comment-list/comment-list.component";
import {
  CommentSectionContainerComponent
} from "../features/comments/components/comment-section-container/comment-section-container.component";
import {CommentPageComponent} from "../features/comments/pages/comment-page/comment-page.component";
import {LoginFormComponent} from "../features/login/components/login-form/login-form.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PostFilterComponent} from "../features/posts/components/post-filter/post-filter.component";

@Component({
  selector: 'app-test-page',
  standalone: true,
  imports: [
    PostUpdatePageComponent,
    CommentInputComponent,
    CommentListComponent,
    CommentSectionContainerComponent,
    CommentPageComponent,
    LoginFormComponent,
    ReactiveFormsModule,
    FormsModule,
    PostFilterComponent
  ],
  templateUrl: './test-page.component.html',
  styleUrl: './test-page.component.css'
})
export class TestPageComponent {

}
