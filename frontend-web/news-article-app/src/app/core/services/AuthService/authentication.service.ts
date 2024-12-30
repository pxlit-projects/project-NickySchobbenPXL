import { Injectable } from '@angular/core';
import { User } from "../../models/user/User";
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private loggedInUserSubject: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);

  constructor() {
    const savedUser = localStorage.getItem('loggedInUser');
    if (savedUser) {
      const user: User = JSON.parse(savedUser);
      this.loggedInUserSubject.next(user);
    }
  }

  login(account: User): void {
    this.loggedInUserSubject.next(account);
    localStorage.setItem('loggedInUser', JSON.stringify(account));
  }

  logout(): void {
    this.loggedInUserSubject.next(null);
    localStorage.removeItem('loggedInUser');
  }

  getLoggedInUserObservable(): Observable<User | null> {
    return this.loggedInUserSubject.asObservable();
  }

  getLoggedInUser(): User | null {
    return this.loggedInUserSubject.value;
  }

  getIsLoggedInValue(): boolean {
    return this.loggedInUserSubject.value !== null;
  }
}
