import {Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {AuthComponent} from "./components/auth/auth.component";


/*
 * preloading strategy:
 * https://dev.to/railsstudent/how-to-lazy-load-routes-and-import-standalone-components-in-angular-4b1a
 */

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'auth',
    pathMatch: 'full',
    loadComponent: () => import('./components/auth/auth.component').then(mod=> mod.AuthComponent)
  },
  {
    path: 'home',
    loadComponent: () => import('./components/home/home.component').then(mod => mod.HomeComponent),
    children: [
      {
        path: 'table',
        pathMatch: "full",
        loadComponent: () => import('./components/home/right/table/table.component').then(mod => mod.TableComponent)
      },
      {
        path: 'part',
        pathMatch: 'full',
        loadComponent: () => import('./components/home/right/part/part.component').then(mod => mod.PartComponent)
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
