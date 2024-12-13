import { Routes } from '@angular/router';
import { PostOverviewPageComponent} from "./features/posts/pages/post-overview-page/post-overview-page.component";
import {PostCreatePageComponent} from "./features/posts/pages/post-create-page/post-create-page.component";
import {PostUnpublishedPageComponent} from "./features/posts/pages/post-unpublished-page/post-unpublished-page.component";
import {TestPageComponent} from "./test-page/test-page.component";
import {PostConceptPageComponent} from "./features/posts/pages/post-concept-page/post-concept-page.component";
import {PostDetailPageComponent} from "./features/posts/pages/post-detail-page/post-detail-page.component";

export const routes: Routes = [
  {path: 'posts', component: PostOverviewPageComponent},
  {path: 'posts/create-new', component: PostCreatePageComponent},
  {path: 'test', component: TestPageComponent},
  {path: 'posts/unpublished', component:PostUnpublishedPageComponent},
  {path: 'posts/concept', component: PostConceptPageComponent},
  { path: 'post/:id', component: PostDetailPageComponent },
];
