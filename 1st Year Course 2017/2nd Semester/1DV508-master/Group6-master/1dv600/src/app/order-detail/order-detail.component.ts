import { Component, OnInit } from '@angular/core';
import { Order } from '../order';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../services/data.service';
import { Product } from '../product';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  order: Order;
  key: string;
  products: Product[];
  user;
  status;

  constructor(private data: DataService, private route: ActivatedRoute, public auth: AuthService) { 
    this.route.paramMap.subscribe(params => {
      this.key = params.get('orderkey');
    });

    data.getOrder(this.key).subscribe(order => {
      this.order = order[0];
    })
  }

  ngOnInit() {
    this.auth.user$.subscribe(user => this.user = user);
  }
  
  editStatus(status: string){
    this.data.updateOrder(this.key, new Order(this.order.uid, status, this.order.products));
  }
}
