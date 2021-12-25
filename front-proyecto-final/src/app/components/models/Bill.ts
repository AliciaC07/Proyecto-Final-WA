import { Event } from "./Event";
import { Product } from "./Product";

export class Bill{
    id!: number;
    userName!: string;
    email!:string;
    eventSelected!: Event;
    totalAmount!: number;
    productsSelected: Product[] = [];
    finished!: boolean;
    orderTransaction!: string;
    allNameProducts:string[] = [];
    date!: string;
}