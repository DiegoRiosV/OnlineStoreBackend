// product-detail.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { AsyncPipe, DecimalPipe, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { switchMap, map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { ProductService } from '../../../core/services/product.service';
import { CartService } from '../../../core/services/cart.service';
import { Product } from '../../../core/models/product.model';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [RouterModule, AsyncPipe, DecimalPipe, NgIf, FormsModule],
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  qty = 1;
  product$!: Observable<Product>;

  constructor(
    private route: ActivatedRoute,
    private api: ProductService,
    private cart: CartService
  ) {}

  ngOnInit(): void {
    this.product$ = this.route.paramMap.pipe(
      map(params => Number(params.get('id'))),  // asegura number
      switchMap(id => this.api.getById(id))
    );
  }

  dec(){ this.qty = Math.max(1, this.qty - 1); }
  inc(){ this.qty = this.qty + 1; }

  add(p: Product){
    this.cart.add({
      productId: p.id,
      nameProduct: p.nameProduct,
      price: p.price,
      imageUrl: p.imageUrl,
      qty: this.qty
    });
  }

  openDrawer(){
    document.querySelector('app-cart-drawer')
      ?.dispatchEvent(new CustomEvent('open'));
  }
}
