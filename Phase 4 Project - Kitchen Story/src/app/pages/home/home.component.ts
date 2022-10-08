import { Component, OnInit, Input } from '@angular/core';
import Product from '../../models/product';
import { ProductService } from '../../services/product/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Array<Product> = [];

 //constructor will injecting a dependency on the productService object
 //inject the activated route inside the property through the constructor
 //we can have access to the activated route throughout the home component
  constructor(private productService:ProductService, private route:ActivatedRoute) {

  }

  ngOnInit(): void {

    //Let's listen to the route parameters using the activated route instance
    this.route.params.subscribe(params => {
      if(params['searchTerm'])
        this.products = this.productService.getAllProductsBySearchTerm(params['searchTerm']);
      else if(params['desiredTag'])
        this.products = this.productService.getAllProductsByTag(params['desiredTag']);
      else
        this.products = this.productService.getAllProducts();
    })
  }

}
