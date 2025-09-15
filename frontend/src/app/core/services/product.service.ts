import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product.model';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private ProductUrl = 'http://localhost:8081/api/products';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.ProductUrl);
  }

  getById(id: string | number): Observable<Product> {
  return this.http.get<Product>(`${this.ProductUrl}/${id}`);
  }


  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.ProductUrl, product);
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.ProductUrl}/${id}`, product);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ProductUrl}/${id}`);
  }
}
