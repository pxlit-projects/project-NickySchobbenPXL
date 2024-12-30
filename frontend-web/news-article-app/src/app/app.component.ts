import { Component, OnInit, OnDestroy } from '@angular/core';
import {Router, NavigationEnd, RouterOutlet} from '@angular/router';
import { AuthenticationService } from "./core/services/AuthService/authentication.service";
import { Subscription } from 'rxjs';
import { User } from "./core/models/user/User";
import {AdminNavbarComponent} from "./shared/admin-navbar/admin-navbar.component";
import {UserNavbarComponent} from "./shared/user-navbar/user-navbar.component";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  imports: [
    AdminNavbarComponent,
    UserNavbarComponent,
    RouterOutlet,
    CommonModule
  ],
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  user: User | null = null;
  showNavbar = false;
  private subscriptions: Subscription = new Subscription();

  constructor(private authService: AuthenticationService, private router: Router) {}

  ngOnInit(): void {
    this.subscriptions.add(
      this.authService.getLoggedInUserObservable().subscribe(user => {
        this.user = user;
        this.showNavbar = !!user;
      })
    );

    this.subscriptions.add(
      this.router.events.subscribe(event => {
        if (event instanceof NavigationEnd) {
          this.showNavbar = this.authService.getIsLoggedInValue();
        }
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
