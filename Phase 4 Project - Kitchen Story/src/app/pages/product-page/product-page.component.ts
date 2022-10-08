import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Product from '../../models/product';
import { CartService } from '../../services/cart/cart.service';
import { ProductService } from '../../services/product/product.service';

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent implements OnInit {

  /* --------- Properties --------- */
  product!:Product;

  /* --------- Constructor --------- */
  constructor(activatedRoute:ActivatedRoute,
              productService:ProductService,
              private cartService:CartService,
              private router:Router ) {
    activatedRoute.params.subscribe((params) => {
      if(params['id'])
        this.product = productService.getProductById(params['id']);
    })
  }
  /* --------- Methods --------- */
  /* *******************************************
   * Method Name: ngOnInit()
   * Access Type: public
   * Input Parameters: none
   * Return Type: void
   * Purpose:
   * ******************************************* */
  ngOnInit(): void {
  }

  /* *******************************************
   * Method Name: addToCart()
   * Access Type: public
   * Input Parameters: none
   * Return Type: void
   * Purpose: Associated method call for the "Add to Cart" button click event on the HTML template
   *          Add the product property to the cart using the cart service; re-direct to the cart page
   * ******************************************* */
  addToCart(){
    this.cartService.addToCart(this.product); //Use the cart service to add the product to the cart
    this.router.navigateByUrl('/cart-page'); //Use the router to re-direct the user to the cart page
  }

}
