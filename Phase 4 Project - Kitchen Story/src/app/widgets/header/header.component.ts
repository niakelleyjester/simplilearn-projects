import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { CartService } from '../../services/cart/cart.service';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  /* --------- Properties --------- */
  cartQuantity = 0;
  @Input() isLoggedIn:boolean; //comes from RegisterComponent
  newUser:User; //comes from the RegisterComonent
  @Input() user:User; //comes from the RegisterComponent

  /* --------- Constructor --------- */
  //inject CartService
  constructor(cartService:CartService, userService:UserService) {
      cartService.getCartObservable().subscribe((newCart) => {
        this.cartQuantity = newCart.totalCount;
      })
   }

   /* --------- Methods --------- */
   ngOnInit(): void {
  }
}
