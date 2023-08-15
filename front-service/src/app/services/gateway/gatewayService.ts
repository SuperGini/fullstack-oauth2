import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Token} from "../../utility/token";
import {Observable} from "rxjs";
import {PartResponsePaginated} from "../../utility/response/partResponsePaginated";
import {PartRequest} from "../../utility/request/partRequest";

@Injectable({providedIn: 'root'})
export class GatewayService {

  private httpClient = inject(HttpClient);


  getAuthToken(url: string, body: any, options: {headers: HttpHeaders }): Observable<Token> {
    return this.httpClient.post<Token>(url, body, options);
  }

  getParts(url: string, options: {headers: HttpHeaders}) {
    return this.httpClient.get<PartResponsePaginated>(url, options);
  }

  createPart(url: string, body: PartRequest, options: {headers: HttpHeaders}){
    return this.httpClient.post(url, body, options);
  }

}
