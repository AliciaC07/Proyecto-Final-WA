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
  todaysChart?: BarData[];
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
    let notAssigned2: BarData = {name:'Not Assigned', value: 0};
    let assigned2: BarData = {name:'Assigned', value: 0};
    let finished2: BarData = {name: 'Finished', value: 0};
    let aux: BarData[] = [];
    let aux2: BarData[] = [];
    aux.push(notAssigned, assigned, finished);
    aux2.push(notAssigned2, assigned2, finished2);
    let today = new Date();
    today.setHours(0);
    today.setMinutes(0);
    today.setSeconds(0);
    today.setMilliseconds(0);
    this.orderService.getAllBills().subscribe({
      next: response => {
        response.forEach(bill =>{
          let billDate = new Date(bill.date);
          billDate.setHours(0)
          billDate.setDate(billDate.getDate()+1);
          switch(bill.status){
            case 'Not assigned': {
              if(today.getTime() === billDate.getTime()){
                aux2[0].value += 1;
              }
              aux[0].value += 1;
              break;
            }

            case 'Assigned': {
              if(today.getTime() === billDate.getTime()){
                aux2[1].value += 1;
              }
              aux[1].value += 1;
              break;
            }

            case 'Finished': {
              if(today.getTime() === billDate.getTime()){
                aux2[2].value += 1;
              }
              aux[2].value += 1;
              break;
            }
          }
        });
        this.single = aux;
        this.todaysChart = aux2;        
      }
    })
  }

  onSelect(event:any) {
    console.log(event);
  }
}
