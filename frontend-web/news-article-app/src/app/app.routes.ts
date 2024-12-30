import { Routes } from '@angular/router';
import { PostOverviewPageComponent} from "./features/posts/pages/post-approved-page/post-overview-page.component";
import {PostCreatePageComponent} from "./features/posts/pages/post-create-page/post-create-page.component";
import {PostUnpublishedPageComponent} from "./features/posts/pages/post-waiting-for-approval-page/post-unpublished-page.component";
import {TestPageComponent} from "./test-page/test-page.component";
import {PostConceptPageComponent} from "./features/posts/pages/post-concept-page/post-concept-page.component";
import {PostDetailPageComponent} from "./features/posts/pages/post-detail-page/post-detail-page.component";
import {PostUpdatePageComponent} from "./features/posts/pages/post-update-page/post-update-page.component";
import {ReviewPageComponent} from "./features/review/pages/review-page/review-page.component";
import {LoginPageComponent} from "./features/login/pages/login-page/login-page.component";
import {UnauthorizedPageComponent} from "./features/errors/pages/unauthorized-page/unauthorized-page.component"
import {NotfoundPageComponent} from "./features/errors/pages/notfound-page/notfound-page.component";
import {PostCategorySportsPageComponent} from "./features/posts/pages/post-category-sports-page/post-category-sports-page.component";
import {PostCategoryRegionalPageComponent} from "./features/posts/pages/post-category-regional-page/post-category-regional-page.component";
import {PostCategorySciencePageComponent} from "./features/posts/pages/post-category-science-page/post-category-science-page.component";
import {PostCategoryAllPageComponent} from "./features/posts/pages/post-category-all-page/post-category-all-page.component";
import {PostCategoryPoliticsPageComponent} from "./features/posts/pages/post-category-politics-page/post-category-politics-page.component";
import {adminAuthGuard} from "./admin-auth-guard.guard";
import {userAuthGuardGuard} from "./user-auth-guard.guard";

export const routes: Routes = [

  // Login Screen
  {path: '', component: LoginPageComponent},

  // Published posts / user navbar
  {path: 'home/posts', component: PostCategoryAllPageComponent,  canActivate: [userAuthGuardGuard]},
  {path: 'home/posts/sport', component: PostCategorySportsPageComponent, canActivate:[userAuthGuardGuard]},
  {path: 'home/posts/regional', component: PostCategoryRegionalPageComponent, canActivate:[userAuthGuardGuard]},
  {path: 'home/posts/politics', component: PostCategoryPoliticsPageComponent, canActivate:[userAuthGuardGuard]},
  {path: 'home/posts/science', component: PostCategorySciencePageComponent, canActivate:[userAuthGuardGuard]},

  // Unpublished posts / admin navbar
  {path: 'posts/approved', component: PostOverviewPageComponent,  canActivate: [adminAuthGuard]},
  {path: 'posts/unpublished', component:PostUnpublishedPageComponent, canActivate: [adminAuthGuard]},
  {path: 'posts/concept', component: PostConceptPageComponent, canActivate: [adminAuthGuard]},
  {path: 'posts/create-new', component: PostCreatePageComponent, canActivate: [adminAuthGuard]},
  {path: 'posts/:id', component: PostDetailPageComponent, canActivate: [userAuthGuardGuard]},
  {path: 'posts/:id/update', component: PostUpdatePageComponent, canActivate: [adminAuthGuard]},
  {path: 'posts/:id/review', component: ReviewPageComponent, canActivate: [adminAuthGuard]},

  // General links
  {path: 'unauthorized', component: UnauthorizedPageComponent},
  {path: 'notfound', component: NotfoundPageComponent},
  {path: 'test', component: TestPageComponent},
  { path: '**', redirectTo: '' },
];
