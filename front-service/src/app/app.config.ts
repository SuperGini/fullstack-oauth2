import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {PreloadAllModules, provideRouter, RouterOutlet, withDebugTracing, withPreloading} from '@angular/router';

import {routes} from './app.routes';
import {provideAnimations} from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import {httpInterceptorProviders} from "./httpInterceptorProviders ";

/**
 * preloading strategy:
 * https://dev.to/railsstudent/how-to-lazy-load-routes-and-import-standalone-components-in-angular-4b1a
 * */

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withPreloading(PreloadAllModules)),
    provideAnimations(),
    importProvidersFrom(HttpClientModule,
      RouterOutlet,
    ),
    /**
     * here we add the interceptor to intercept request -> the order matters
     * https://stackoverflow.com/questions/50211120/angular-6-provide-http-interceptors-for-root
     * https://angular.io/guide/http-intercept-requests-and-responses
     * */
    httpInterceptorProviders

  ]
};
