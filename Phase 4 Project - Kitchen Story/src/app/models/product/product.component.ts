import { Component, Input, OnInit } from '@angular/core';
import Product from '../product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  // decorate the 'data' property with @Input(); the values will come from the parent
  @Input() data: Product = {
    productId:0,
    imageUrl: '',
    productName: '',
    price: 0,
    brand: '',
    rating: 0,
    numOfReviews: 0,
    description: '',
    //favorite: false,
    organic:false,
    tags: []
    };


  constructor() {
   }

  ngOnInit(): void {
  }

}
