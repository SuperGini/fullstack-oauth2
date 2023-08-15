import {PartResponse} from "./partResponse";

export interface PartResponsePaginated {
  nrOfParts: number
  partResponses: PartResponse[];
  total: number;

}
