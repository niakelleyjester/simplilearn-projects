import { Component, Input, OnInit } from '@angular/core';
import { Tag } from '../../models/Tag';
import { ProductService } from '../../services/product/product.service';


@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit {

  //properties
  @Input()
  productPageTags?:string[];

  @Input()
  justifyContent:string = 'center';

  tags?:Tag[];

  constructor(private productService:ProductService) { }

  ngOnInit(): void {
    if(!this.productPageTags)
      this.tags = this.productService.getAllTags();
  }

}
