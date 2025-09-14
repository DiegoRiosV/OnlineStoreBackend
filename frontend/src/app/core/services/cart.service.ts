import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItem } from '../models/product.model';

@Injectable({ providedIn: 'root' })
export class CartService {
  private items = new Map<string | number, CartItem>();

  private readonly countSub = new BehaviorSubject<number>(0);
  readonly count$ = this.countSub.asObservable();
  get count(): number {
    return this.countSub.value;
  }
  get snapshot(): CartItem[] {
    return [...this.items.values()];
  }
  add(item: CartItem): void {
    const curr = this.items.get(item.productId);
    const qty = (curr?.qty ?? 0) + (item.qty ?? 1);
    const merged: CartItem = { ...item, qty };
    this.items.set(item.productId, merged);
    this.recalcCount();
  }

  private recalcCount(): void {
    const total = [...this.items.values()].reduce((acc, it) => acc + it.qty, 0);
    this.countSub.next(total);
  }
}
