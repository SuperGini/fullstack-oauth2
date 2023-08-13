import { Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {AuthComponent} from "./components/auth/auth.component";

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'auth',
    component: AuthComponent,
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent,
    pathMatch: "full"
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'authorized',
    redirectTo: 'auth',
    pathMatch: 'full'
  },
  {
    path: '*',
    redirectTo: 'auth',
    pathMatch: 'full'
  }


];
