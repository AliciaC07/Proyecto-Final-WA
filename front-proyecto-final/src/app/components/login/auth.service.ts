import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from './models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public userEndPoint: string = "http://localhost:9000";
  public eventEndPoint: string = "http://localhost:8083/api";
  private _user?: User;
  private _token?: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private router:Router) { }
  
  public get user(): User{
    if(this._user != null){
      return this._user;
    } else if(this._user == null && sessionStorage.getItem('user')!= null){
     this._user = JSON.parse(sessionStorage.getItem('user')!) as User;
     return this._user;
    }
    return new User();
  }

  public get token(): string{
    if(this._token != null){
      return this._token;
    } else if(this._token == null && sessionStorage.getItem('token')!= null){
     this._token = sessionStorage.getItem('token')!;
     return this._token;
    }
    return "";
  }

  login(user: User): Observable<any>{
    const urlEndpoint =`${this.userEndPoint}/auth/login`;

    let params = {
      username: `${user.username}`,
      password: `${user.password}`
    }

    return this.http.post<any>(urlEndpoint, params);
  }

  saveUserCreds(response: any): void {
    this._user = new User();
    this._user.id = response.id;
    this._user.name = response.name;
    this._user.username = response.username;
    this._user.lastName = response.lastName;
    this._user.email = response.email;
    this._user.role = response.role;

    this._token = response.token;

    sessionStorage.setItem('user', JSON.stringify(this._user));
    sessionStorage.setItem('token', this._token!);
  }

  logout(): void{
    this._token = undefined;
    this._user = undefined;
    sessionStorage.clear();
    this.router.navigate(['home']);
  }

  hasRole(role: string){
    if(this.isAuthenticated()){
      if(this.user.role?.name === role){
        return true;
      }
    }
    return false;
  }

  isAuthenticated(): boolean{
    let payload = this.user;
    if(payload != null && payload.name && payload.name.length > 0){
      return true;
    }
    return false;
  }

  appendAuthorization() {
    let token = this.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

}
