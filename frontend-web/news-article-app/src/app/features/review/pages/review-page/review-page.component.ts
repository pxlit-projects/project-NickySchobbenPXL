import {Component, inject} from '@angular/core';
import {PostItemComponent} from "../../../posts/components/post-item/post-item.component";
import {ReviewFormComponent} from "../../components/review-form/review-form.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-review-page',
  standalone: true,
  imports: [
    PostItemComponent,
    ReviewFormComponent
  ],
  templateUrl: './review-page.component.html',
  styleUrl: './review-page.component.css'
})
export class ReviewPageComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  postId: number = this.route.snapshot.params['id'];
}
