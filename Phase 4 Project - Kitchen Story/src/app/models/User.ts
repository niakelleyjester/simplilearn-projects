import { Address } from "./Address";

export class User{
  /* --------- Properties (based on the API) --------- */
  userId!:number;
  name!:string;
  username!:string;
  password!:string;
  address:Address;
  email!:string;
  isAdmin!:boolean;
}
