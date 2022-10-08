import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, TitleStrategy } from '@angular/router';
import { ProductService } from 'src/app/services/product/product.service';
import Product from '../../models/product';


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  /* --------- Properties --------- */
  addProductForm:FormGroup;
  submitted:boolean = false;
  oldProductCount:number=0;
  newProduct:Product = {
    productId:0,
    imageUrl: '',
    productName: '',
    price: 0,
    brand: '',
    rating: 0,
    numOfReviews: 0,
    description: '',
    organic:false,
    tags: []
    };
  baseLocation:String = "/assets/images/";

  /* --------- Constructor --------- */
  constructor(fb: FormBuilder,
              private productService:ProductService,
              private router:Router) {
    this.addProductForm = fb.group({
      pname: ['', Validators.required],
      pprice: ['', Validators.required],
      pbrand: [''],
      prating:['', Validators.compose([Validators.min(0),Validators.max(5)])],
      pnumrevs:[''],
      pimagename:['', Validators.required],
      pdescription:[''],
      porganic:[''],
      ptags:['']
    })
   }

  /* --------- Methods --------- */
  ngOnInit(): void {
  }

  /* *******************************************
   * Method Name: getFormControls()
   * Access Type: public
   * Input Parameters: none
   * Return Type: none
   * Purpose: Getter method to get all of the form controls
   * ******************************************* */
    get getFormControls(){
      return this.addProductForm.controls;
    }

  onSubmit(){
    this.submitted = true;

    if(this.addProductForm.invalid)
      return; //don't allow the user to submit the form
    else{
      this.refineProductData(this.newProduct);
      this.productService.addProduct(this.newProduct);
      console.log(this.newProduct);
      alert("Successfully added a new product...");
      this.router.navigate(['/home']);
      //this.addProductForm.reset(); //having a problem with the method; submitting the form automatically
    }
  }

  refineProductData(p:Product){
    p.productId = this.productService.getAllProductsCount() + 1;
    p.imageUrl = this.baseLocation + p.imageUrl;
    //p.tags = this.addProductForm.controls['ptags'].value; //tags assignment has a bug. to be fixed in future release
  }

  onChange($event:any){
    return this.newProduct.organic ? false : true;
  }

}
