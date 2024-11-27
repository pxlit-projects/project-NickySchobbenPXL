import {Component, inject} from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-admin-navbar',
  standalone: true,
  imports: [

  ],
  templateUrl: './admin-navbar.component.html',
  styleUrl: './admin-navbar.component.css'
})
export class AdminNavbarComponent {
  router: Router = inject(Router);

  navigateToNewPost(): void {
    this.router.navigate(['/posts/create-new']);
  }
}
