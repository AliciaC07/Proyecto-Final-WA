import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../login/auth.service';
import { Bill } from '../../models/Bill';
import { OrderService } from '../../plan-viewer-selector/order.service';

@Component({
  selector: 'app-list-jobs',
  templateUrl: './list-jobs.component.html',
  styleUrls: ['./list-jobs.component.css']
})
export class ListJobsComponent implements OnInit {

  orders: Bill[] = []
  constructor(private authService: AuthService, private orderService: OrderService) { }

  ngOnInit(): void {
    this.orderService.getAllBills().subscribe({
      next: response =>{
        this.orders = response.reverse();
      }
    });
  }

  assignJob(order: Bill){
    
  }

}
