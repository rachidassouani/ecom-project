import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductDTO } from '../../models/product-dto';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { ProductResponse } from '../../models/product-response';
import { ProductRequest } from '../../models/product-request';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  private readonly productsUrl = `${environment.api.baseUrl}/${environment.api.productUrl}`;
  private readonly productsByCategoryIdUrl = `${environment.api.baseUrl}/${environment.api.productsByCategoryUrl}`;


  findAllProducts(): Observable<ProductDTO[]> {
    return this.httpClient.get<ProductDTO[]>(this.productsUrl);
  }

  findAllProductsByCategoryId(categoryId: number): Observable<ProductDTO[]> {
    return this.httpClient.get<ProductDTO[]>(`${this.productsByCategoryIdUrl}/${categoryId}`);
  }

  deleteProduct(productId: number | undefined): Observable<void> {
    return this.httpClient.delete<void>(`${this.productsUrl}/${productId}`);
  }

  saveProduct(productRequest: ProductRequest): Observable<ProductResponse> {
    return this.httpClient.post<ProductResponse>(this.productsUrl, productRequest);
  }
}
