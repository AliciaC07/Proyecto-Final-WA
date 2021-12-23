import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PlanViewerSelectorComponent } from './components/plan-viewer-selector/plan-viewer-selector.component';
import { RecordComponent } from './components/record/record.component';
import { CreateUserComponent } from './components/users/create-user/create-user.component';
import { ListUserComponent } from './components/users/list-user/list-user.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'create-user/:action', component: CreateUserComponent},
  {path:'create-user/:action/:id', component: CreateUserComponent},
  {path:'list-user', component: ListUserComponent}, // probablemente sea mejor ponerlo en la vista de create user directamente
  {path:'record/:id', component: RecordComponent},
  {path:'plans', component: PlanViewerSelectorComponent},
  {path:'', pathMatch:'full', redirectTo:'home'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
