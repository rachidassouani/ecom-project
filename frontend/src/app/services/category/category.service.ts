import { Injectable } from '@angular/core';
import { CategoryDTO } from '../../models/category-dto';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';
import { CategoryRequest } from '../../models/category-request';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) { }

  private readonly categoryUrl = `${environment.api.baseUrl}/${environment.api.categoryUrl}`;



  findAllCategories(): Observable<CategoryDTO[]> {
    return this.httpClient.get<CategoryDTO[]>(this.categoryUrl);
  }

  deleteCategory(categoryId: number | undefined): Observable<void> {
    return this.httpClient.delete<void>(`${this.categoryUrl}/${categoryId}`);
  }

  saveCategory(categoryRequest: CategoryRequest): Observable<CategoryRequest> {
    return this.httpClient.post<CategoryRequest>(this.categoryUrl, categoryRequest);
  }

}
