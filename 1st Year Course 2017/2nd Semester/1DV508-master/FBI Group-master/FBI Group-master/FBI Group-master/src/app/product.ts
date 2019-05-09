export class Product{
    name: string;
    brand: string;
    category: string;
    color: string;
    price: string;
    pictureURL: string;
    constructor(name: string, brand: string, category: string, color: string, price: string, pictureURL: string){
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.color = color;
        this.price = price;
        this.pictureURL = pictureURL;
    }

}