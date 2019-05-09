import { Product } from "./product";

export class Order{
    uid: string;
    status: string;
    products: Product[];
    constructor(uid: string, status: string, products: Product[]){
        this.uid = uid;
        this.status = status;
        this.products = products;
    }

}