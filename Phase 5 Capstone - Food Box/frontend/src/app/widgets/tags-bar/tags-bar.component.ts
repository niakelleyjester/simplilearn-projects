import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute} from '@angular/router';
import Product from 'src/app/models/Product';
import { TagsBarService } from 'src/app/services/tags-bar/tags-bar.service';
import { Tag } from '../../models/Tag';

@Component({
  selector: 'app-tags-bar',
  templateUrl: './tags-bar.component.html',
  styleUrls: ['./tags-bar.component.css']
})
export class TagsBarComponent implements OnInit {

  //Properties
  @Input()
  productPageTags?:string[]; //should be used on the individual product page. nullable; mirrors the Product tags String Array

  @Input()
  justifyContent:string = 'center';

  tags?:Tag[]; //default Tags Bar; should be used on the home component/landing page

  //Constructor
  constructor(private tagsBarService:TagsBarService) {

    if(!this.productPageTags) //if the productPageTags are not available, load all the tags from the database
      this.tagsBarService.getAllTags().subscribe(
        (serverTags: Tag[]) => {
          this.tags = serverTags;
          console.log("Tags from database:");
          console.log(serverTags);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }

        );
  }//end constructor

  //Methods
  /* ngOnInit() is only called once */
  ngOnInit(): void {
  }//end ngOnInit()

}//end class
