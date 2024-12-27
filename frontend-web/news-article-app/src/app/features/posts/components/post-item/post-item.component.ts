import {Component, Input} from '@angular/core';
import {Post} from "../../../../core/models/posts/Post";
import {CommonModule, NgClass} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-post-item',
  standalone: true,
  imports: [
    NgClass,
    RouterLink,
    CommonModule
  ],
  templateUrl: './post-item.component.html',
  styleUrl: './post-item.component.css'
})
export class PostItemComponent {
  @Input() post!: Post;
}
