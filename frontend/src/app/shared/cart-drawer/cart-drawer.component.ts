import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CartItem } from '../../core/models/product.model';
import { CartService } from '../../core/services/cart.service';
import {PaymentModalComponent} from './../payment-modal/payment-modal.component'
@Component({
  selector: 'app-cart-drawer',
  imports: [PaymentModalComponent],
  templateUrl: './cart-drawer.component.html',
  styleUrls: ['./cart-drawer.component.css']
})
export class CartDrawerComponent implements OnInit {
  items$!: Observable<CartItem[]>;
  total$!: Observable<number>;

  openDrawer: boolean = false;   
  showPayment: boolean = false;  

  constructor(private cart: CartService) {}

  ngOnInit(): void {
    this.items$ = this.cart.items$;
    this.total$ = this.cart.total$;
  }

  openPayment() {
    this.showPayment = true;
  }

  closePayment() {
    this.showPayment = false;
  }

  closeDrawer() {
    this.openDrawer = false; 
  }
  processPayment(method: string) {
    console.log('Método elegido:', method);

    //this.http.post('http://localhost:8081/api/payments', { method, items: ... })

    this.closePayment();
    alert(`Pago con ${method} procesado exitosamente 🚀`);
  }
}
