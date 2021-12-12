import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '@environments/environment';

import { Role } from '@models/enums/role.enum';

const TOKEN_KEY = environment.token_key;
const USER_KEY = environment.user_key;

@Injectable({
  providedIn: 'root',
})
export class TokenStorageService {
  constructor(private router: Router) {}

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY) || '';
  }

  public saveToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public saveClient(user: any): void {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  public isLoggedIn(): boolean {
    const token: string = this.getToken();
    return token ? true : false;
  }

  public getCurrentUser(): any {
    const user = localStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

  public isAdmin(): any {
    return this.isLoggedIn() && this.getCurrentUser().roles === Role.ADMIN;
  }
}
