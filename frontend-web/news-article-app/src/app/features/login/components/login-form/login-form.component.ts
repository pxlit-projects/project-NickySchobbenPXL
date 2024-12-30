import {Component, inject} from '@angular/core';
import {User} from "../../../../core/models/user/User";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthenticationService} from "../../../../core/services/AuthService/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {
  serv: AuthenticationService = inject(AuthenticationService);
  router: Router = inject(Router);
  incorrectCredentials = "";
  users: User[] = [
    new User('admin', 'admin', 'admin'),
    new User('user', 'user', 'user'),
  ];

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(2)]),
    password: new FormControl('', [Validators.required, Validators.minLength(2)]),
  });


  onSubmit() {
    console.log(this.loginForm.value.username);
    for (const user of this.users) {
      if (user.username == this.loginForm.value.username && user.password == this.loginForm.value.password) {
        this.serv.login(user);
        this.router.navigate(['/home/posts']);
        return;
      }
    }
    this.incorrectCredentials = 'Username or password is incorrect or does not exist.';
  }
}
