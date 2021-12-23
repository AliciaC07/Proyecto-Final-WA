import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { CreateUserComponent } from './components/users/create-user/create-user.component';
import { ListUserComponent } from './components/users/list-user/list-user.component';
import { PlanViewerSelectorComponent } from './components/plan-viewer-selector/plan-viewer-selector.component';
import { CartComponent } from './components/users/cart/cart.component';
import { RecordComponent } from './components/record/record.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    CreateUserComponent,
    ListUserComponent,
    PlanViewerSelectorComponent,
    CartComponent,
    RecordComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
