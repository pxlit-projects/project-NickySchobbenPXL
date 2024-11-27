import { Routes } from '@angular/router';
import { PostOverviewPageComponent} from "./features/posts/pages/post-overview-page/post-overview-page.component";
import {PostCreateorupdatePageComponent} from "./features/posts/pages/post-createorupdate-page/post-createorupdate-page.component";

export const routes: Routes = [
  {path: 'posts', component: PostOverviewPageComponent},
  {path: 'posts/create-new', component: PostCreateorupdatePageComponent}
];
