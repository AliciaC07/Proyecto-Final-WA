import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PlanViewerSelectorComponent } from './components/plan-viewer-selector/plan-viewer-selector.component';
import { CreateUserComponent } from './components/users/create-user/create-user.component';
import { ListUserComponent } from './components/users/list-user/list-user.component';
import { ViewPurchasedComponent } from './components/users/view-purchased/view-purchased.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'create-user', component: CreateUserComponent},
  {path:'list-user', component: ListUserComponent}, // probablemente sea mejor ponerlo en la vista de create user directamente
  {path:'view-purchased/:id', component: ViewPurchasedComponent},
  {path:'shopping-cart', component: PlanViewerSelectorComponent},
  {path:'', pathMatch:'full', redirectTo:'home'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
