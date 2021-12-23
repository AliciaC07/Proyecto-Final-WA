import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { Bill } from '../models/Bill';
import { Event } from '../models/Event';
import { OrderService } from './order.service';

@Component({
  selector: 'app-plan-viewer-selector',
  templateUrl: './plan-viewer-selector.component.html',
  styleUrls: ['./plan-viewer-selector.component.css']
})
export class PlanViewerSelectorComponent implements OnInit {

  bill?:Bill;

  events: Event[] = [];
  constructor(private authService: AuthService, private router: Router, private orderService: OrderService) { }

  ngOnInit(): void {
    this.bill = this.orderService.bill;
    this.orderService.getAllEvents().subscribe({
      next: (response) =>{
         this.events = response;
      }
    })
  }

  /*
    prewedding id == 1
    wedding id == 2
    birthday id == 3
    video shoot id == 4
  */ 

    createCart(eId: number): void{
      let event:Event = new Event();
      event = this.events.find(({ id }) => id == eId)!;

      this.bill = new Bill();
      this.bill.eventSelected = event;
      this.bill.userName = this.authService.user.username;

      this.orderService.saveBill(this.bill);
      this.router.navigate(['/shopping-cart', this.bill.eventSelected.id]);
      console.log(this.bill);
    }

}
