import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListJobsComponent } from './components/employees/list-jobs/list-jobs.component';
import { HomeComponent } from './components/home/home.component';
import { AuthService } from './components/login/auth.service';
import { LoginComponent } from './components/login/login.component';
import { PlanViewerSelectorComponent } from './components/plan-viewer-selector/plan-viewer-selector.component';

import { CartComponent } from './components/users/cart/cart.component';
import { CreateUserComponent } from './components/users/create-user/create-user.component';
import { ListUserComponent } from './components/users/list-user/list-user.component';
import { RecordComponent } from './components/users/record/record.component';
import { AuthGuardGuard } from './shared/Guards/auth-guard.guard';
import { RoleGuardGuard } from './shared/Guards/role-guard.guard';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'create-user/:action', component: CreateUserComponent, canActivate: [AuthGuardGuard]},
  {path:'create-user/:action/:id', component: CreateUserComponent, canActivate: [RoleGuardGuard], data: {role:['Admin']}},
  {path:'list-user', component: ListUserComponent, canActivate: [RoleGuardGuard], data:{role:['Admin']}}, // probablemente sea mejor ponerlo en la vista de create user directamente
  {path:'record/:username', component: RecordComponent, canActivate: [AuthGuardGuard]},
  {path:'list-jobs', component: ListJobsComponent, canActivate: [RoleGuardGuard], data:{role:['Admin', 'Employee']}},
  {path:'plans', component: PlanViewerSelectorComponent},
  {path:'shopping-cart/:id', component: CartComponent, canActivate:[AuthGuardGuard]},
  {path:'', pathMatch:'full', redirectTo:'home'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
