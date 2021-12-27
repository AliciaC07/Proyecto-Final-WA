import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../login/auth.service';
import { Signup } from '../../login/models/dtos/Signup';
import { User } from '../../login/models/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: Signup = new Signup;

  title:string = "";
  isClient: boolean = true;
  btnString: string = ""
  param: string ="";
  userId?: number;

  constructor(private router: Router, private acttivatedRoute: ActivatedRoute, private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.acttivatedRoute.params.subscribe(param =>{
      this.param = param['action'];
      switch(this.param){
        case 'sign-up': {
          this.title = "Sign Up"
          this.btnString = "Sign Up"
          break;
        }
        case 'employee': {
          if(!this.authService.hasRole('Admin')){
            this.router.navigate(['/home']);
          }
          this.title = "Register Employee"
          this.isClient = false;
          this.btnString = "Create"
          break;
        }

        case 'edit':{
          if(!this.authService.hasRole('Admin')){
            this.router.navigate(['/home']);
          }
          this.title = "Modify User";
          this.isClient = false;
          this.btnString = "Update";
          this.acttivatedRoute.params.subscribe(param =>{
            this.userId = param['id'];
            if(this.userId){
              this.userService.getUser(this.userId).subscribe({
                next: (response) => {
                  this.user = response;
                },
                error: e =>{
                  alert(e.message);
                }
              });
            }
          });
        }
      }
    });
  }

  btnAction(){
    switch(this.param){
      case 'sign-up': {
        this.user.role = "Client";
        this.userService.registerUser(this.user).subscribe({
          next: () =>{
            this.router.navigate(['/home']);
          },
          error: (e) =>{
            alert(e.message);
          }
        })
        break;
      }
      
      case 'employee':{
        this.user.role = "Employee";
        this.userService.registerUser(this.user).subscribe({
          next: () =>{
            this.router.navigate(['/home']);
          },
          error: (e) =>{
            alert(e.message);
          }
        })
        break;
      }

      case 'edit':{
        this.userService.putUser(this.userId!, this.user).subscribe({
          next: (r)=>{ this.router.navigate(['/home']) },
          error: (e) =>{ alert(e.messasge) }
        });
        break;
      }
    }
  }

}
