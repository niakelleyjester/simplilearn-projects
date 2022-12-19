import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';
import { CartService } from '../../services/cart/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  //Properties
  cartQuantity = 0;


  //Constructor
  constructor(cartService:CartService,
              public loginService:LoginService) {
      console.log("Inside Home Component Constructor");

      cartService.getCartObservable().subscribe((newCart) => {
        this.cartQuantity = newCart.totalCount;
      })
   }

   //Methods
   ngOnInit(): void {
  }


}//end class
