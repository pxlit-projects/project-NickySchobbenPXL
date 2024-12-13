import {Component, inject} from '@angular/core';
import { Router } from '@angular/router'
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-admin-navbar',
  standalone: true,
  imports: [
    NgIf

  ],
  templateUrl: './admin-navbar.component.html',
  styleUrl: './admin-navbar.component.css'
})
export class AdminNavbarComponent {
  router: Router = inject(Router);
  isDropdownOpen = false;

  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  navigateToNewPost(): void {
    this.router.navigate(['/posts/create-new']);
  }
}
