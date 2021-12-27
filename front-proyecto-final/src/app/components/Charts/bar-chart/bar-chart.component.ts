import { Component, OnInit, AfterViewInit, ElementRef, ViewChild, OnDestroy } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NavigationEnd, Router } from '@angular/router';
import { Bar, NgxChartsModule } from '@swimlane/ngx-charts';
import { OrderService } from '../../plan-viewer-selector/order.service';
import { BarData } from '../model/single';


@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit {
  single?: BarData[];
  view: any[] = [700,400];
  //options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Status';
  showYAxisLabel = true;
  yAxisLabel = 'Orders';

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };
  mySubscription: any;

  constructor(private orderService: OrderService, private router: Router) { 

   }
  
  ngOnInit(): void {
    let notAssigned: BarData = {name:'Not Assigned', value: 0};
    let assigned: BarData = {name:'Assigned', value: 0};
    let finished: BarData = {name: 'Finished', value: 0};
    let aux: BarData[] = [];
    aux.push(notAssigned, assigned, finished);
    this.orderService.getAllBills().subscribe({
      next: response => {
        response.forEach(bill =>{
          switch(bill.status){
            case 'Not assigned': {
              aux[0].value += 1;
              break;
            }

            case 'Assigned': {
              aux[1].value += 1;
              break;
            }

            case 'Finished': {
              aux[2].value += 1;
              break;
            }
          }
        });
        this.single = aux;        
      }
    })
  }

  onSelect(event:any) {
    console.log(event);
  }
}
