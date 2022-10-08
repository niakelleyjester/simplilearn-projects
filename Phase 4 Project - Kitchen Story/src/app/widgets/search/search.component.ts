import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; /* ActivatedRoute: is for reading from the route; Router: is for writing into the route*/

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  //properties (variables)
  searchTerm:String = ""; //default value is empty

  //inject the activated route inside the constructor
  constructor(private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if(params['searchTerm']) //params.searchTerm resulted an error
      this.searchTerm = params['searchTerm'];
    })
  }

  search():void{

    //will use the router inside of the search method
    if(this.searchTerm)
      this.router.navigateByUrl('/search/' + this.searchTerm);

  }

}
