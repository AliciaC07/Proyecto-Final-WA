import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../login/auth.service';
import { Bill } from '../../models/Bill';
import { Product } from '../../models/Product';
import { OrderService } from '../../plan-viewer-selector/order.service';
import { render } from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  @ViewChild('closeModal') closeModal: any;

  availableproducts: Product[] = []
  products: Product[] = [];
  bill?: Bill;
  selectedProducts: Product[] = [];

  imageUri: string = "";
  imageTitle: String = "";
  offers: string[]= []

  constructor(private authService: AuthService, private orderService: OrderService, private activatedRoute: ActivatedRoute,
    private router: Router) { }

  // esto viene del localstorage o será mejor hacer  una bd en el front para esto ?
  ngOnInit(): void {
    this.bill = this.orderService.bill;
    this.availableproducts = this.orderService.bill.eventSelected.products;
    this.activatedRoute.params.subscribe(param =>{
      switch(+param['id']){
        case 1: {
          this.imageUri = "../../../../assets/prewedding1.jpg"
          this.imageTitle = "Prewedding photoshoot";
          this.offers = ['Base photoshoot', '5-10 photo album'];
          break;
        }

        case 2: {
          this.imageUri = "../../../../assets/wedding2.jpg"
          this.imageTitle = "Wedding photoshoot";
          this.offers = ['Base photoshoot', '5-10 photo album'];
          break;
        }

        case 3: {
          this.imageUri = "../../../../assets/birthday1.jpg"
          this.imageTitle = "Birthday photoshoot";
          this.offers = ['Base photoshoot', '5-10 photo album'];
          break;
        }

        case 4: {
          this.imageUri = "../../../../assets/event2.jpg"
          this.imageTitle = "Event videoshoot";
          this.offers = ['Base photoshoot', '5-10 photo album'];
          break;
        }

        default: {
          if(this.authService.isAuthenticated()){
            this.router.navigate(['/plans']);
          }else{
            this.router.navigate(['/login']);
          }
          break;
        }
      }
    })
  }

  addRemove(product: Product) {
    if(this.selectedProducts.includes(product)){
      this.selectedProducts = this.selectedProducts.filter( prod => prod.id !== product.id)
    }else{
      this.selectedProducts.push(product);
    }
  }

  addtoBill() {
    this.bill!.productsSelected = this.selectedProducts;
    this.bill!.totalAmount = this.bill!.eventSelected.price;
    this.bill!.productsSelected.forEach(p =>{
      this.bill!.totalAmount += 250;
    })
    this.orderService.saveBill(this.bill);
    console.log(this.orderService.bill);
    this.removeAllChildren(document.getElementById("paypal-btn"));
    
    render({
      id: '#paypal-btn',
      currency: 'DOP',
      value: `${this.bill?.totalAmount}`,
      onApprove: (p) =>{
        console.log(p);
        this.bill!.orderTransaction = p.id;
        this.confirmPayment(this.orderService.bill);
      }
    })
  }

  removeAllChildren(element: any){
    while(element.firstChild){
      element.removeChild(element.firstChild);
    }
  }

  confirmPayment(bill: Bill){
    this.orderService.savePayment(bill).subscribe({
      next: p =>{
        let bill = new Bill();
        this.orderService.saveBill(bill);
        this.closeModal.nativeElement.click();
        this.router.navigate(['/home']);
      }
    });
  }
}
