import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'src/app/models/product/product';
import { Category } from 'src/app/models/product/category';

const API_URL_PRODUCTS = "http://localhost:7000/api/products";
const API_URL_CATEGORY = "http://localhost:7000/api/category";
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(API_URL_PRODUCTS);
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(API_URL_CATEGORY);
   }

  getAllProductsByCategory(theCategoryName: string): Observable<Product[]> {
    const searchUrl = `${API_URL_CATEGORY}/search?category=${theCategoryName}`;
    return this.http.get<Product[]>(searchUrl);
  }


  getProductById(theProductId: number): Observable<Product> {
    //need to build URL based on product id
    const productUrl = `${API_URL_PRODUCTS}/${theProductId}`;
    return this.http.get<Product>(productUrl);
  }

  searchProducts(theKeyword: string): Observable<Product[]> {
    //need to build URL based on the keyword http://localhost:9000/api/products/search?product=ful
    const searchUrl = `${API_URL_PRODUCTS}/search?product=${theKeyword}`;
    return this.http.get<Product[]>(searchUrl);
  }




}
