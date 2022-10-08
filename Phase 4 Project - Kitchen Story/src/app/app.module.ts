import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './widgets/header/header.component';
import { ProductComponent } from './models/product/product.component';
import { FooterComponent } from './widgets/footer/footer.component';
import { TruncatePipe } from './truncate.pipe';
import { SearchComponent } from './widgets/search/search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TagsComponent } from './widgets/tags/tags.component';
import { ProductPageComponent } from './pages/product-page/product-page.component';
import { CartPageComponent } from './pages/cart-page/cart-page.component';
import { NotFoundComponent } from './widgets/not-found/not-found.component';
import { StarRatingModule } from 'angular-star-rating';
import { RegisterComponent } from './forms/register/register.component';
import { CheckoutPageComponent } from './pages/checkout-page/checkout-page.component';
import { OrderConfirmationComponent } from './pages/order-confirmation/order-confirmation.component';
import { LoginComponent } from './forms/login/login.component';
import { LoginService } from './services/login/login.service';
import { AddProductComponent } from './forms/add-product/add-product.component';
import { RemoveProductComponent } from './forms/remove-product/remove-product.component';
import { ResetPasswordComponent } from './forms/reset-password/reset-password.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    ProductComponent,
    FooterComponent,
    TruncatePipe,
    SearchComponent,
    TagsComponent,
    ProductPageComponent,
    CartPageComponent,
    NotFoundComponent,
    RegisterComponent,
    CheckoutPageComponent,
    OrderConfirmationComponent,
    LoginComponent,
    AddProductComponent,
    RemoveProductComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    StarRatingModule.forRoot()
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
