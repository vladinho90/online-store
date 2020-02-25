import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/product/category';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-category-menu',
  templateUrl: './category-menu.component.html',
  styleUrls: ['./category-menu.component.css']
})
export class CategoryMenuComponent implements OnInit {

  categories: Category[];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.listCategories();
  }

  listCategories() {
    this.productService.getAllCategories().subscribe(
      (data: Category[]) => {
        this.categories = data;
      }
    );
  }


}
