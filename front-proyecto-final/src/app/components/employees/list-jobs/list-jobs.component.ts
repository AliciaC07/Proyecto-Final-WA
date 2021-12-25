import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../login/auth.service';
import { AssignedDto } from '../../models/AssignedDto';
import { Bill } from '../../models/Bill';
import { OrderService } from '../../plan-viewer-selector/order.service';

@Component({
  selector: 'app-list-jobs',
  templateUrl: './list-jobs.component.html',
  styleUrls: ['./list-jobs.component.css']
})
export class ListJobsComponent implements OnInit {

  orders: Bill[] = []
  constructor(private authService: AuthService, private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {
    this.orderService.getAllBills().subscribe({
      next: response =>{
        this.orders = response.reverse();
      }
    });
  }

  assignJob(order: Bill){
    let assignJob: AssignedDto = new AssignedDto;

    assignJob.id = order.id;
    switch(order.status){
      case 'Not Assigned':{
        assignJob.status = "Assigned";
        break;
      }
      case 'Assigned': {
        assignJob.status = "Finished";
        break;
      }
    }
    assignJob.employee = this.authService.user.username;
    this.orderService.assignJob(assignJob).subscribe({
      next: () =>{
        this.router.navigate(['list-jobs']);
      }
    });
  }

}
