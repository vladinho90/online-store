import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { ProductCategory } from 'src/app/common/product-category';

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css']
})
export class ProductCategoryMenuComponent implements OnInit {

  productCategories: ProductCategory[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.listProductCategories();
  }

  listProductCategories(){
    this.productService.getProductCategories().subscribe(
      (data: ProductCategory[]) => { 
        this.productCategories = data;
        console.log(this.productCategories);
      }
    );
  }

}
