import {inject, Injectable} from "@angular/core";
import {GatewayService} from "./gateway/gatewayService";
import {HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PartResponsePaginated} from "../utility/response/partResponsePaginated";
import {PartRequest} from "../utility/request/partRequest";

@Injectable({providedIn: 'root'})
export class PartService {

  private gateway = inject(GatewayService);


  getPartsWithPagination(pageIndex: number, pageSize: number): Observable<PartResponsePaginated>{
    const url = `http://localhost:8081/parts/${pageIndex}/${pageSize}`;
    console.log(pageIndex + ' ++ ' +pageSize);
    const options = this.setHeaders();

    return this.gateway.getParts(url, options);


  }

  createPart (partRequest: PartRequest){
    const url = `http://localhost:8081/part`;
    const options = this.setHeaders();
    console.log(partRequest)
    return  this.gateway.createPart(url, partRequest, options).subscribe();

    ;

  }


  private setHeaders() {
    const token = sessionStorage.getItem('id_token');
    const bearerToken = `Bearer ${token}`;
    const headers = new HttpHeaders()
                                            .set('Authorization', bearerToken)
                                            .set('content-type', 'application/json');
    return {headers: headers};
  }

}