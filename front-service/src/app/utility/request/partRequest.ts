import {CarRequest} from "./carRequest";

export interface PartRequest {
  partName: string;
  quantity: number;
  price: string;
  carRequest: CarRequest;
}
