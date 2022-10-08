import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product/product.service';
import Product from '../../models/product';

@Component({
  selector: 'app-remove-product',
  templateUrl: './remove-product.component.html',
  styleUrls: ['./remove-product.component.css']
})
export class RemoveProductComponent implements OnInit {

  /* --------- Properties --------- */
  products:Product[];

  /* --------- Constructor --------- */
  constructor(private productService:ProductService, private router:Router) {
    this.products = this.productService.getAllProducts();
  }

  /* --------- Methods --------- */
  ngOnInit(): void {
  }

  deleteProduct(x:number){
    console.log(this.productService.getProductById(x));
    this.productService.removeProductById(x);
    alert("Removed Product from the Kitchen Story inventory...");
    this.router.navigate(['/home']);
  }

}
