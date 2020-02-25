import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product/product';
import { ProductService } from 'src/app/services/product/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];
  currentCategoryName: string;
  searchMode: boolean;

  //inject our product service
  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }
  //the current active route that loaded the componenet, useful for accessing route parameters

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
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

  handleListProducts() {
    //check if "name" parameter is available
    const hasCategoryName: boolean = this.route.snapshot.paramMap.has('name');
    if (hasCategoryName) {
      //get the "name" param string
      this.currentCategoryName = this.route.snapshot.paramMap.get('name');
    } else {
      //category name is not availalbe ... default to category shoes
      this.currentCategoryName = 'SHOES';
    }
    //now get the products for the given cateoogry id
    this.productService.getAllProductsByCategory(this.currentCategoryName).subscribe(
      (data: Product[]) => {
        this.products = data; //save Product [] from observable;
      }
    );
  }

  handleSearchProducts() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');
    //now search for the products using keyword
    this.productService.searchProducts(theKeyword).subscribe(
      (data: Product[]) => {
        this.products = data;
      }
    );
  }

}
