import { Event } from "./Event";
import { Product } from "./Product";

export class Bill{
    id!: number;
    userName!: string;
    email!:string;
    eventSelected!: Event;
    totalAmount!: number;
    productsSelected: Product[] = [];
    status: string = 'Not assigned'; // ** Not assigned -> assigned -> finished
    orderTransaction!: string;
    allNameProducts:string[] = [];
    date!: string;
}