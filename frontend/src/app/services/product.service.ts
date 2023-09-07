import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductDTO } from '../models/product-dto';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  private readonly productsUrl = `${environment.api.baseUrl}/${environment.api.productUrl}`;


  findAllProducts(): Observable<ProductDTO[]> {
    return this.httpClient.get<ProductDTO[]>(this.productsUrl);
  }

  deleteProduct(productId: number | undefined): Observable<void> {
    return this.httpClient.delete<void>(`${this.productsUrl}/${productId}`);
  }

}
