import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgIf, DecimalPipe } from '@angular/common';   // 👈 agrega DecimalPipe
import { CartService } from '../../core/services/cart.service';

@Component({
  selector: 'app-payment-modal',
  standalone: true,
  imports: [FormsModule, NgIf, DecimalPipe],          // 👈 y aquí
  templateUrl: './payment-modal.component.html',
  styleUrls: ['./payment-modal.component.css']
})
export class PaymentModalComponent {
  @Input() total: number = 0;
  @Input() clientId!: number;
  @Output() close = new EventEmitter<void>();

  selectedMethod: 'paypal' | 'creditcard' = 'paypal';
  loading = false;

  // PayPal
  email = '';
  password = '';

  // Credit card
  cardNumber = '';
  cardHolder = '';
  expirationDate = ''; // MM/AA
  cvv = '';

  constructor(private cart: CartService) {}

  isValidPaypal(): boolean {
    return /\S+@\S+\.\S+/.test(this.email) && this.password.length >= 4;
  }

  isValidCard(): boolean {
    const numOk = /^\d{13,19}$/.test(this.cardNumber.replace(/\s+/g, ''));
    const holderOk = this.cardHolder.trim().length >= 4;
    const expOk = /^(0[1-9]|1[0-2])\/\d{2}$/.test(this.expirationDate);
    const cvvOk = /^\d{3,4}$/.test(this.cvv);
    return numOk && holderOk && expOk && cvvOk;
  }

  payNow() {
    if (this.total <= 0) { alert('El carrito está vacío.'); return; }

    if (this.selectedMethod === 'paypal' && !this.isValidPaypal()) return;
    if (this.selectedMethod === 'creditcard' && !this.isValidCard()) return;

    this.loading = true;

    // Vacía carrito en BD (tu CartService ya llama al endpoint /api/cart/{id}/clear)
    this.cart.clear();

    setTimeout(() => {
      this.loading = false;
      alert('✅ ¡Compra exitosa! Gracias por tu pago.');
      this.close.emit();
    }, 300);
  }
}
