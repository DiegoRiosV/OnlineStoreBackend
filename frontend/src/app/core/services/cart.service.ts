import { Injectable } from '@angular/core';
import { BehaviorSubject, map, combineLatest, Observable } from 'rxjs';
import { CartItem } from '../models/product.model';
import { CartApiService } from './cart-api.service';
import { ProductService } from './product.service';

const CART_ID = 1; // usa el id real del carrito

@Injectable({ providedIn: 'root' })
export class CartService {
  private _rawItems$ = new BehaviorSubject<CartItem[]>([]);

  // se inicializan en el constructor (para no usar dependencias antes de tiempo)
  readonly items$: Observable<CartItem[]>;
  readonly count$: Observable<number>;
  readonly total$: Observable<number>;

  constructor(private api: CartApiService, private products: ProductService) {
    // Enriquecer carrito con imagen del catálogo y dar SIEMPRE un string (fallback)
    this.items$ = combineLatest([this._rawItems$, this.products.indexById$]).pipe(
      map(([items, idx]) =>
        items.map(i => {
          const p = idx.get(Number(i.productId));
          const imageUrl =
            (p?.imageUrl) ??
            (p?.idProduct ? `assets/img/${p.idProduct}.png` : 'assets/img/placeholder.png');
          return { ...i, imageUrl }; // ← siempre string
        })
      )
    );

    this.count$ = this.items$.pipe(map(xs => xs.reduce((s, i) => s + i.qty, 0)));
    this.total$ = this.items$.pipe(map(xs => xs.reduce((s, i) => s + i.qty * Number(i.price), 0)));

    this.refresh();
  }

  private refresh() {
    this.api.getCart(CART_ID).subscribe((items: CartItem[]) => this._rawItems$.next(items ?? []));
  }

  add(item: CartItem) {
    this.api.addItem({ cartId: CART_ID, productId: item.productId, quantity: item.qty })
      .subscribe({ next: () => this.refresh() });
  }

  update(productId: number, qty: number) {
    this.api.updateQtyByProduct(CART_ID, productId, Math.max(1, qty))
      .subscribe({ next: () => this.refresh() });
  }

  remove(productId: number) {
    this.api.removeByProduct(CART_ID, productId)
      .subscribe({ next: () => this.refresh() });
  }

  clear() {
    this.api.clear(CART_ID).subscribe({ next: () => this.refresh() });
  }
}
