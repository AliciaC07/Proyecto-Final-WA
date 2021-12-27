import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { User } from './models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  user: User = new User;

  constructor(private authService:AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(): void {

    this.authService.login(this.user).subscribe({
      next: (response) =>{
        this.authService.saveUserCreds(response);
        this.router.navigate(['/home']);
      },
      error: (e) =>{
        if(e.status == 401 || 403){
          alert('Username or password incorrect');
        }
      }
    })
    /*
    this.authService.login(this.user).subscribe(response => {
      this.authService.saveUserCreds(response);
      //route to dashboard or main or maybe the cart ?
      this.router.navigate(['/home']);
    }, err =>{
      if(err.status == 401 || 400){
        alert('Username or Password incorrect');
      }
    })*/
  }

}
