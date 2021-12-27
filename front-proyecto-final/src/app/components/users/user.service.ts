import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../login/auth.service';
import { Signup } from '../login/models/dtos/Signup';
import { User } from '../login/models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private authService: AuthService, private http:HttpClient) { }

  registerUser(user: Signup): Observable<Signup>{
    return this.http.post<Signup>(`${this.authService.userEndPoint}/auth/signup`, user,{headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  getUser(id: number): Observable<Signup>{
    return this.http.get<Signup>(`${this.authService.userEndPoint}/user-id/${id}`, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(`${this.authService.userEndPoint}/users`, {headers:this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  putUser(id: number, user: Signup):Observable<Signup>{
    return this.http.put<Signup>(`${this.authService.userEndPoint}/user/${id}`, user,{headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

  deleteUser(username: string):Observable<any>{
    return this.http.delete<any>(`${this.authService.userEndPoint}/user/${username}`, {headers: this.authService.appendAuthorization()}).pipe(
      catchError(e =>{
        return throwError(() => e);
      })
    );
  }

}
