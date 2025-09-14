import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { CartItem } from '../models/product.model';

const CART_KEY = 'biba.cart.v1';

@Injectable({ providedIn: 'root' })
export class CartService {
  private _items$ = new BehaviorSubject<CartItem[]>(this.load());
  readonly items$ = this._items$.asObservable();
  readonly count$ = this.items$.pipe(map(items => items.reduce((s,i)=> s+i.qty, 0)));
  readonly total$ = this.items$.pipe(map(items => items.reduce((s,i)=> s+i.qty*i.price, 0)));

  private load(): CartItem[] {
    try { return JSON.parse(localStorage.getItem(CART_KEY) || '[]'); } catch { return []; }
  }
  private save(v: CartItem[]) { localStorage.setItem(CART_KEY, JSON.stringify(v)); }

  add(item: CartItem) {
    const list = [...this._items$.value];
    const idx = list.findIndex(x => x.productId === item.productId);
    if (idx > -1) list[idx] = { ...list[idx], qty: list[idx].qty + item.qty };
    else list.push(item);
    this._items$.next(list); this.save(list);
  }

  update(productId: CartItem['productId'], qty: number) {
    const list = this._items$.value.map(x => x.productId === productId ? { ...x, qty: Math.max(1, qty) } : x);
    this._items$.next(list); this.save(list);
  }

  remove(productId: CartItem['productId']) {
    const list = this._items$.value.filter(x => x.productId !== productId);
    this._items$.next(list); this.save(list);
  }

  clear(){ this._items$.next([]); this.save([]); }
}
