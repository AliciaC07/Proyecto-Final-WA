import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../login/auth.service';
import { User } from '../../login/models/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[] = []

  constructor(private authservice: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (response) =>{
        this.users = response;
      }
    })
  }

  deleteUser(username:string){
    this.userService.deleteUser(username).subscribe({
      next:(response) =>{
        this.userService.getAllUsers().subscribe({
          next: (res) =>{
            this.users = res;
          }
        })
      },
      error:(e) =>{
        alert(e.message);
      }
    });
  }

}
