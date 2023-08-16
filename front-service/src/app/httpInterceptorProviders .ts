import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ContentTypeInterceptor} from "./interceptor/contentType.interceptor";
import {TokenInterceptor} from "./interceptor/token.interceptor";
import {AuthInterceptor} from "./interceptor/auth.interceptor";

export const httpInterceptorProviders = [

  {
    provide: HTTP_INTERCEPTORS,
    useClass: ContentTypeInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }
]

