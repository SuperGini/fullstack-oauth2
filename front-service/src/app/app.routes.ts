import { Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {AuthComponent} from "./components/auth/auth.component";
import {PartComponent} from "./components/home/right/part/part.component";
import {TableComponent} from "./components/home/right/table/table.component";


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
    children: [
      {
        path: 'table',
        component: TableComponent,
        pathMatch: "full"
      },
      {
        path: 'part',
        component: PartComponent,
        pathMatch: 'full'
      }
    ]
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
