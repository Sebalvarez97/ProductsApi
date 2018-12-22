import { Component, OnInit } from '@angular/core';
import { IProduct } from 'src/app/products/product';
import { ProductService } from 'src/app/products//product.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'pm-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  producto: IProduct;

  crearProducto(){
    
  }

  ngOnInit() {
  }

}
