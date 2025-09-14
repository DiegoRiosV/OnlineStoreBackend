import { Component, HostListener, OnInit } from '@angular/core';
import { NgFor, NgIf, AsyncPipe, DecimalPipe } from '@angular/common';
import { CartService } from '../../core/services/cart.service';
import { Observable } from 'rxjs';
import { CartItem } from '../../core/models/product.model';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-cart-drawer',
  standalone: true,
  imports: [NgFor, NgIf, AsyncPipe, DecimalPipe, FormsModule],
  templateUrl: './cart-drawer.component.html',
  styleUrls: ['./cart-drawer.component.css']
})
export class CartDrawerComponent implements OnInit {
  openDrawer = false;

  items$!: Observable<CartItem[]>;
  total$!: Observable<number>;

  constructor(private cart: CartService) {}

  ngOnInit(): void {
    this.items$ = this.cart.items$;
    this.total$ = this.cart.total$;
  }

  @HostListener('open') onOpen(){ this.openDrawer = true; }
  close(){ this.openDrawer = false; }
  remove(id: any){ this.cart.remove(id); }
  update(id: any, qty: number){ this.cart.update(id, qty); }
}
