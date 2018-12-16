import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IProduct } from './product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private httpClient: HttpClient){}

  getProducts(): Observable<IProduct[]> {
    return this.httpClient.get<IProduct[]>("http://localhost:8080/api/products");
  }

}
