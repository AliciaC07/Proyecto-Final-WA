import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../login/auth.service';
import { Bill } from '../../models/Bill';
import { OrderService } from '../../plan-viewer-selector/order.service';

@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit {
  
  bills: Bill[] = [];
  title: string = ""
  constructor(private authService: AuthService, private orderService:OrderService, private activatedRoute: ActivatedRoute) { }
  
  //Create on the orderService a function to call 1 username
  //BTW do the other one that pulls all the orders/records
  
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params =>{
        switch(params['username']){
          case 'my-orders':{
            this.title = "Orders"
            this.orderService.getBillsUser(this.authService.user.username).subscribe({
              next: response =>{
                this.bills = response.reverse();
              }
            });
            break;
          }
          default: {
            this.title = `Orders of ${params['username']}`;
            this.orderService.getBillsUser(params['username']).subscribe({
              next: response =>{
                this.bills = response.reverse();
              }
            });
            break;
          }
        }
      });
  }

}
