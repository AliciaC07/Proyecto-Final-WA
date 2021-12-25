import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListJobsComponent } from './components/employees/list-jobs/list-jobs.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PlanViewerSelectorComponent } from './components/plan-viewer-selector/plan-viewer-selector.component';

import { CartComponent } from './components/users/cart/cart.component';
import { CreateUserComponent } from './components/users/create-user/create-user.component';
import { ListUserComponent } from './components/users/list-user/list-user.component';
import { RecordComponent } from './components/users/record/record.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'create-user/:action', component: CreateUserComponent},
  {path:'create-user/:action/:id', component: CreateUserComponent},
  {path:'list-user', component: ListUserComponent}, // probablemente sea mejor ponerlo en la vista de create user directamente
  {path:'record/:username', component: RecordComponent},
  {path:'list-jobs', component: ListJobsComponent},
  {path:'plans', component: PlanViewerSelectorComponent},
  {path:'shopping-cart/:id', component: CartComponent},
  {path:'', pathMatch:'full', redirectTo:'home'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
