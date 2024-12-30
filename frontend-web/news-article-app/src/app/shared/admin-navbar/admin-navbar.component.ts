import {Component, inject} from '@angular/core';
import {Router, RouterLink} from '@angular/router'
import {NgIf} from "@angular/common";
import {AuthenticationService} from "../../core/services/AuthService/authentication.service";

@Component({
  selector: 'app-admin-navbar',
  standalone: true,
  imports: [
    NgIf,
    RouterLink

  ],
  templateUrl: './admin-navbar.component.html',
  styleUrl: './admin-navbar.component.css'
})
export class AdminNavbarComponent {
  router: Router = inject(Router);
  isDropdownOpen = false;
  serv: AuthenticationService = inject(AuthenticationService);

  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  navigateTo(route: string): void {
    this.isDropdownOpen = false;
    this.router.navigate([route]);
  }

  logOut() {
    this.serv.logout();
    this.router.navigate([''])
  }
}
