import { ProductCategory } from './../common/product-category';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:9000/api/products';
  private categoryUrl = 'http://localhost:9000/api/category';

  constructor(private httpClient: HttpClient) { }


  getAllProducts(theCategoryName: string): Observable<Product[]> {
    const searchUrl = `${this.categoryUrl}/search?category=${theCategoryName}`;

    return this.getProducts(searchUrl);
  }



  getProductCategories(): Observable<ProductCategory[]> {

    return this.httpClient.get<ProductCategory[]>(this.categoryUrl);
  }

  searchProducts(theKeyword: string): Observable<Product[]> {
    //need to build URL based on the keyword http://localhost:9000/api/products/search?product=ful
    const searchUrl = `${this.baseUrl}/search?product=${theKeyword}`;

    return this.getProducts(searchUrl);
  }

  getProduct(theProductId: number): Observable<Product> {
    //need to build URL based on product id
    const productUrl = `${this.baseUrl}/${theProductId}`;
    
    return this.httpClient.get<Product>(productUrl);
  }

  getProductById(id: number): Observable<Product>{
    return this.httpClient.get<Product>(`http://localhost:9000/api/products/${id}`)
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    console.log(this.httpClient.get<Product[]>(searchUrl));
    return this.httpClient.get<Product[]>(searchUrl);
  }
}
