import {Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {AuthComponent} from "./components/auth/auth.component";
import {canActivateRoute} from "./guards/auht.guard";
import {NotFoundComponent} from "./components/notfound/not.found.component";


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
    loadComponent: () => import('./components/auth/auth.component').then(mod=> mod.AuthComponent) //lazy loading
  },
  {
    path: 'home',
    loadComponent: () => import('./components/home/home.component').then(mod => mod.HomeComponent),
    canActivate: [canActivateRoute], //guard
    children: [
      {
        path: 'table',
        pathMatch: "full",
        loadComponent: () => import('./components/home/right/table/table.component').then(mod => mod.TableComponent),
        canActivate: [canActivateRoute]
      },
      {
        path: 'part',
        pathMatch: 'full',
        loadComponent: () => import('./components/home/right/part/part.component').then(mod => mod.PartComponent),
        canActivate: [canActivateRoute]
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
  },
  {
    path: '**',
    component: NotFoundComponent,

  }



];
