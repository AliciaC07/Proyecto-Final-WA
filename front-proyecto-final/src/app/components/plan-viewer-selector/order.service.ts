import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../login/auth.service';
import { AssignedDto } from '../models/AssignedDto';
import { Bill } from '../models/Bill';
import { Event } from '../models/Event';
import { Product } from '../models/Product';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private _bill?: Bill;

  constructor(private authService: AuthService, private http:HttpClient) { }

  public get bill(): Bill{
    if(this._bill != null){
      return this._bill;
    } else if(this._bill == null && sessionStorage.getItem('bill')!= null){
     this._bill = JSON.parse(sessionStorage.getItem('bill')!) as Bill;
     return this._bill;
    }
    return new Bill();
  }

  saveBill(response: any): void {
    this._bill = new Bill();
    this._bill = response;

    sessionStorage.setItem('bill', JSON.stringify(this._bill));
  }

  getAllProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(`${this.authService.eventEndPoint}/products`, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  getAllEvents():Observable<Event[]>{
    return this.http.get<Event[]>(`${this.authService.eventEndPoint}/events`, {headers:this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  savePayment(postBill: Bill): Observable<Bill>{
    return this.http.post<Bill>(`${this.authService.eventEndPoint}/order`, postBill, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  getBillsUser(username: string): Observable<Bill[]>{
    return this.http.get<Bill[]>(`${this.authService.eventEndPoint}/client/orders/${username}`, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  getAllBills(): Observable<Bill[]> {
    return this.http.get<Bill[]>(`${this.authService.eventEndPoint}/orders`, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  assignJob(assignJob: AssignedDto): Observable<AssignedDto>{
    return this.http.post<AssignedDto>(`${this.authService.eventEndPoint}/change-status`, assignJob, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }
}
