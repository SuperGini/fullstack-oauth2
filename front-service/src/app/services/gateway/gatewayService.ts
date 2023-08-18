import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Token} from "../../utility/token";
import {Observable} from "rxjs";
import {PartResponsePaginated} from "../../utility/response/partResponsePaginated";
import {PartRequest} from "../../utility/request/partRequest";

@Injectable({providedIn: 'root'})
export class GatewayService {

  private httpClient = inject(HttpClient);


  getAuthToken(url: string, body: any): Observable<Token> {
    return this.httpClient.post<Token>(url, body);
  }

  getParts(url: string) {
    return this.httpClient.get<PartResponsePaginated>(url);
  }

  createPart(url: string, body: PartRequest){
    return this.httpClient.post(url ,body);
  }

  regenerateAuthToken(url: string, body: any): Observable<Token>{
    return this.httpClient.post<Token>(url, body);
  }

}
