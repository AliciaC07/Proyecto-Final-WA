import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor() { }

  // esto viene del localstorage o será mejor hacer  una bd en el front para esto ?
  ngOnInit(): void {
    
  }

}
