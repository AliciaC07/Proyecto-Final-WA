import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './components/login/auth.service';
import { OrderService } from './components/plan-viewer-selector/order.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-proyecto-final';
  constructor(private authService: AuthService, private orderService: OrderService, private router: Router){ }

  hasRole(role: string): boolean{ return this.authService.hasRole(role) }

  isAuth(): boolean{ return this.authService.isAuthenticated() }

  logout() { this.authService.logout() }

  //the cart button need to redirect only if ther is a cart if not do nothing or raise an alert that the cart is empty
  checkCart(){
    if(this.orderService.bill.eventSelected == null || undefined){
      alert("The cart is currently empty");
    }else{
      this.router.navigate(['/shopping-cart', this.orderService.bill.eventSelected.id])
    }
  }
}
