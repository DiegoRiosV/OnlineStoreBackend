import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-payment-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './payment-modal.component.html',
  styleUrls: ['./payment-modal.component.css']
})
export class PaymentModalComponent {
  http = inject(HttpClient);

  method: 'credit-card' | 'paypal' = 'credit-card';
  cardNumber = '';
  paypalEmail = '';

  pay() {
    const url = `http://localhost:8081/api/payments/${this.method}`;
    const body = this.method === 'credit-card'
      ? { type: 'credit-card', number: this.cardNumber }
      : { type: 'paypal', email: this.paypalEmail };

    this.http.post(url, body).subscribe({
      next: (res) => alert('Pago exitoso!'),
      error: (err) => alert('Error en el pago: ' + err.message)
    });
  }
}
