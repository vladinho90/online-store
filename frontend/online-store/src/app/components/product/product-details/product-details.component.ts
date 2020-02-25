import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product/product.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product/product';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product = new Product();
  constructor(private productService: ProductService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        this.handleProductDetails();
      }
    );
  }

  handleProductDetails() {
    //get the "id" param string . convert string to a numbers using the + symbol
    const theProductId: number = +this.route.snapshot.paramMap.get(`id`);

    this.productService.getProductById(theProductId).subscribe(
      (data: Product) => {
        this.product = data;
      });
  }

}
