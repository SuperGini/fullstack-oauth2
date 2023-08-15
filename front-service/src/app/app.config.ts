import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {provideRouter, RouterOutlet} from '@angular/router';

import { routes } from './app.routes';
import { provideAnimations } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ContentTypeInterceptor} from "./interceptor/contentType.interceptor";
import {AuthInterceptor} from "./interceptor/auth.interceptor";


export const appConfig: ApplicationConfig = {
  providers: [
      provideRouter(routes),
      provideAnimations(),
      importProvidersFrom(HttpClientModule,
          RouterOutlet,
      ),
    /**
     * here we add the interceptor to intercept request -> the order matters
     * https://stackoverflow.com/questions/50211120/angular-6-provide-http-interceptors-for-root
     * */
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ContentTypeInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }

  ]
};
