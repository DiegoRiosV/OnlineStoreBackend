import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { CartItem } from '../models/product.model';
import { CartApiService } from './cart-api.service';

const CART_ID = 1;

@Injectable({ providedIn: 'root' })
export class CartService {
  private _items$ = new BehaviorSubject<CartItem[]>([]);
  readonly items$ = this._items$.asObservable();
  readonly count$ = this.items$.pipe(map(items => items.reduce((s,i)=> s + i.qty, 0)));
  readonly total$ = this.items$.pipe(map(items => items.reduce((s,i)=> s + i.qty * Number(i.price), 0)));

  constructor(private api: CartApiService) {
    this.refresh();
  }

  private refresh() {
    this.api.getCart(CART_ID).subscribe((items: CartItem[]) => {
      this._items$.next(items ?? []);
    });
  }

  add(item: CartItem) {
    this.api.addItem({ cartId: CART_ID, productId: item.productId, quantity: item.qty })
      .subscribe({ next: () => this.refresh() });
  }

  // ------- opción 1: backend por productId -------
  update(productId: number, qty: number) {
    this.api.updateQtyByProduct(CART_ID, productId, Math.max(1, qty))
      .subscribe({ next: () => this.refresh() });
  }

  remove(productId: number) {
    this.api.removeByProduct(CART_ID, productId)
      .subscribe({ next: () => this.refresh() });
  }
  // -----------------------------------------------

  clear() {
    this.api.clear(CART_ID).subscribe({ next: () => this.refresh() });
  }
}
