import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Token} from "../utility/token";
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class GatewayService {

  private httpClient = inject(HttpClient);


  getAuthToken(url: string, body: any, options: { headers: HttpHeaders }): Observable<Token> {
    return this.httpClient.post<Token>(url, body, options);
  }

}
