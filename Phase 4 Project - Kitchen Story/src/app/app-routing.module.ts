import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartPageComponent } from './pages/cart-page/cart-page.component';
import { CheckoutPageComponent } from './pages/checkout-page/checkout-page.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './forms/login/login.component';
import { OrderConfirmationComponent } from './pages/order-confirmation/order-confirmation.component';
import { ProductPageComponent } from './pages/product-page/product-page.component';
import { RegisterComponent } from './forms/register/register.component';
import { CartService } from './services/cart/cart.service';
import { LoginService } from './services/login/login.service';
import { ProductService } from './services/product/product.service';
import { UserService } from './services/user/user.service';
import { AddProductComponent } from './forms/add-product/add-product.component';
import { RemoveProductComponent } from './forms/remove-product/remove-product.component';
import { ResetPasswordComponent } from './forms/reset-password/reset-password.component';

//Declare your routes in the constant routes array
//Specify the path of the route and the component that maps to it
const routes: Routes = [
  {path:'', component:RegisterComponent},
  {path:'home', component:HomeComponent},
  {path: 'search/:searchTerm', component:HomeComponent}, //we are showing our products inside of the home component for this application
  {path: 'tag/:desiredTag', component:HomeComponent}, //localhost:4200/tag/pantry
  {path: 'product/:id', component:ProductPageComponent},
  {path: 'cart-page', component:CartPageComponent},
  {path: 'login', component:LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'checkout', component:CheckoutPageComponent},
  {path: 'order-confirmation', component:OrderConfirmationComponent},
  {path: 'add', component:AddProductComponent},
  {path: 'remove', component:RemoveProductComponent},
  {path: 'resetpw', component:ResetPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [ProductService,
              UserService,
              CartService,
              LoginService] //adding the ProductService to the providers of the root module will make the service avaiable everywhere
})
export class AppRoutingModule { }
