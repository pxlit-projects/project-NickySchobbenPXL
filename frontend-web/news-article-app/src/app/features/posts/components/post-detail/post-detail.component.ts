import {Component, inject, Input, OnInit} from '@angular/core';
import {Post} from "../../../../core/models/Post";

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [],
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent {
  @Input() post!: Post
}
