import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'starFilter'
})
export class StarFilterPipe implements PipeTransform {

  transform(products: any, starNumber: any): any {
    if (starNumber == undefined){
      return products;
    }
    return products.filter(function(product){
      return product.avgStar.toLowerCase().includes(starNumber.toLowerCase());
    })
  }

}
