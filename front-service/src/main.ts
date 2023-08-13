import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

/**
 * not to myself -> never put a button in a form when doing a redirect with window.location.href, it will not work, use <div>.
 * https://stackoverflow.com/questions/2624941/ns-binding-aborted-javascript-window-location-replace
 * */
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
