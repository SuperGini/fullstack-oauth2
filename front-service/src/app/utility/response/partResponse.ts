import {CarResponse} from "./carResponse";

export interface PartResponse {
  id: string;
  partName: string;
  price: string;
  quantity: number;
  partTotal: number;
  car: CarResponse;
}
