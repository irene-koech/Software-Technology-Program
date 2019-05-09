export class Product{
    name: string;
    brand: string;
    category: string;
    color: string;
    price: number;
    stock: number;
    pictureURL: string;
    saleStatus: boolean;
    salePrice: number;
    avgStar: number;
    

    constructor(
        name: string, 
        brand: string, 
        category: string, 
        color: string, 
        price: number, 
        stock: number, 
        pictureURL: string,
        saleStatus: boolean,
        salePrice: number,
        avgStar: number

    ){

        this.name = name;
        this.brand = brand;
        this.category = category;
        this.color = color;
        this.price = price;
        this.stock = stock;
        this.pictureURL = pictureURL;
        this.saleStatus = saleStatus;
        this.salePrice = salePrice;
        this.avgStar = avgStar;
    }

}