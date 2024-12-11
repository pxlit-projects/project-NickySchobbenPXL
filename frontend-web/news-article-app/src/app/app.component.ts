import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {AdminNavbarComponent} from "./shared/admin-navbar/admin-navbar.component";
import {ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AdminNavbarComponent, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'news-article-app';
}
