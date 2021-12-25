import { Component } from '@angular/core';
import { AuthService } from './components/login/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-proyecto-final';
  constructor(private authService: AuthService){ }

  hasRole(role: string): boolean{ return this.authService.hasRole(role) }

  isAuth(): boolean{ return this.authService.isAuthenticated() }

  logout() { this.authService.logout() }

  //the cart button need to redirect only if ther is a cart if not do nothing or raise an alert that the cart is empty
}
