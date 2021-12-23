import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private router: Router, private acttivatedRoute: ActivatedRoute, private userService: UserService) { }

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
          this.title = "Register Employee"
          this.isClient = false;
          this.btnString = "Create"
          break;
        }

        case 'edit':{
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
      case 'Client': {
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
      
      case 'Employee':{
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
