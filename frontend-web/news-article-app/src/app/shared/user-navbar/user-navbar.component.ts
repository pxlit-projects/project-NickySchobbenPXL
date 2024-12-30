import {Component, inject} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../core/services/AuthService/authentication.service";

@Component({
  selector: 'app-user-navbar',
  standalone: true,
  imports: [],
  templateUrl: './user-navbar.component.html',
  styleUrl: './user-navbar.component.css'
})
export class UserNavbarComponent {
  router: Router = inject(Router);
  serv: AuthenticationService = inject(AuthenticationService);

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  logOut() {
    this.serv.logout();
    this.router.navigate([''])
  }
}
