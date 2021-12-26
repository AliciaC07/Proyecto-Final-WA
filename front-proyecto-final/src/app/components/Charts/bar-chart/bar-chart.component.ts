import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { OrderService } from '../../plan-viewer-selector/order.service';
import { BarData } from '../model/single';


@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit, AfterViewInit {
  double: BarData[] = [];
  single: BarData[] = [];
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

  constructor(private orderService: OrderService) { 

   }
  
  ngOnInit(): void {
    let notAssigned: BarData = {name:'Not Assigned', value: 0};
    let assigned: BarData = {name:'Assigned', value: 0};
    let finished: BarData = {name: 'Finished', value: 0};
    this.single.push(notAssigned, assigned, finished);
    this.orderService.getAllBills().subscribe({
      next: response => {
        response.forEach(bill =>{
          switch(bill.status){
            case 'Not Assigned': {
              this.single[0].value += 1;
              break;
            }

            case 'Assigned': {
              this.single[1].value += 1;
              break;
            }

            case 'Finished': {
              this.single[2].value += 1;
              break;
            }
          }
        });        
      }
    })
    if(localStorage.getItem('reload') === 'true'){
      window.location.reload()
      localStorage.setItem('reload','false');
    }
  }

  ngAfterViewInit(): void {
      this.double = this.single;
  }

  onSelect(event:any) {
    console.log(event);
  }
}
