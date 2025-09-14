import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItem } from '../models/product.model';

@Injectable({ providedIn: 'root' })
export class CartService {
  private items = new Map<string | number, CartItem>();

  private readonly countSub = new BehaviorSubject<number>(0);
  readonly count$ = this.countSub.asObservable();

  get count(): number { return this.countSub.value; }
  get snapshot(): CartItem[] { return [...this.items.values()]; }

  add(item: CartItem): void {
    const curr = this.items.get(item.productId);
    const qty = (curr?.qty ?? 0) + (item.qty ?? 1);
    this.items.set(item.productId, { ...item, qty });
    this.recalcCount();
  }

  /** Establece cantidad exacta (si llega <=0 elimina) */
  setQty(productId: CartItem['productId'], qty: number): void {
    if (qty <= 0) { this.remove(productId); return; }
    const curr = this.items.get(productId);
    if (!curr) return;
    this.items.set(productId, { ...curr, qty });
    this.recalcCount();
  }

  remove(productId: CartItem['productId']): void {
    this.items.delete(productId);
    this.recalcCount();
  }

  clear(): void {
    this.items.clear();
    this.recalcCount();
  }

  private recalcCount(): void {
    const total = [...this.items.values()].reduce((a, b) => a + b.qty, 0);
    this.countSub.next(total);
  }
}
