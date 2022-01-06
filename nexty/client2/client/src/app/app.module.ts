import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { LogoutComponent } from './login/logout/logout.component';
import { DemandantesComponent } from './demandantes/components/demandantes/demandantes.component';
import { DemandantesFormComponent } from './demandantes/components/demandantes-form/demandantes-form.component';
import { HomeComponent } from './home/home.component';
import {LoginComponent} from "./login/login/login.component";
import {authInterceptorProviders} from "./helpers/auth.interceptors";
@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    LogoutComponent,
    DemandantesComponent,
    DemandantesFormComponent,
    HomeComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
    ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
