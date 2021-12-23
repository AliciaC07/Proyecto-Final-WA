import { Product } from "./Product";

export class Event{
    id!: number;
    name!: string;
    price!: number;
    products: Product[] = [];
}