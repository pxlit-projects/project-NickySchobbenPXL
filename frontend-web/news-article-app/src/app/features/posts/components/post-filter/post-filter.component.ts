import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PostFilter} from "../../../../core/models/filter/PostFilter";

@Component({
  selector: 'app-post-filter',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './post-filter.component.html',
  styleUrl: './post-filter.component.css'
})
export class PostFilterComponent {
  filter: PostFilter = new PostFilter("", "");

  @Output() filterChange: EventEmitter<PostFilter> = new EventEmitter<PostFilter>();

  public filterPosts(): void {
    console.log("Step 1: post-filter is working");
    this.filterChange.emit(this.filter);
    this.resetFilterValue();
  }

  public inputFieldIsActive():boolean {
    return this.filter.field != "";
  }

  public resetFilterValue() {
    this.filter.value = "";
  }
}
