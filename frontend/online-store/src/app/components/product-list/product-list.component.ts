import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  //templateUrl: './product-list.component.html',
  // templateUrl: './product-list-table.component.html',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];
  currentCategoryName: string;
  searchMode: boolean;

  //inject our product service
  constructor(private productService: ProductService, private route: ActivatedRoute) { }
  //the current active route that loaded the componenet, useful for accessing route parameters


  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      this.listProducts();

    });
  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if (this.searchMode) {
      this.handleSearchProducts();
    } else {
      this.handleListProducts();
    }
  }
  handleSearchProducts() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');
    console.log(theKeyword);
    //now search for the products using keyword
    this.productService.searchProducts(theKeyword).subscribe(
      (data: Product[]) => {
        this.products = data;
      });

  }

  handleListProducts() {

    //check if "name" parameter is avaiallbe
    const hasCategoryName: boolean = this.route.snapshot.paramMap.has('name');

    if (hasCategoryName) {
      //get the "name" param string
      this.currentCategoryName = this.route.snapshot.paramMap.get('name');

    }
    else {
      //not category name is not avaialble ... default to category SHOES
      this.currentCategoryName = "SHOES";
      
    }
    //now get the products for the given category id
    this.productService.getAllProducts(this.currentCategoryName).subscribe(
      (data: Product[]) => {
        this.products = data; //save Product [] from observable  
      });
  }
    
}


