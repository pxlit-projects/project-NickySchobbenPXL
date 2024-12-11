import { Routes } from '@angular/router';
import { PostOverviewPageComponent} from "./features/posts/pages/post-overview-page/post-overview-page.component";
import {PostCreatePageComponent} from "./features/posts/pages/post-create-page/post-create-page.component";

export const routes: Routes = [
  {path: 'posts', component: PostOverviewPageComponent},
  {path: 'posts/create-new', component: PostCreatePageComponent}
];
