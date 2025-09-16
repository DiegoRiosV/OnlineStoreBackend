import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-payment-modal',
  standalone: true,
  imports: [FormsModule],   
  templateUrl: './payment-modal.component.html',
  styleUrls: ['./payment-modal.component.css']
})
export class PaymentModalComponent {
  @Input() total: number = 0; 
  @Input() clientId!: number; 
  @Output() close = new EventEmitter<void>();

  email: string = '';
  password: string = '';
  cardNumber: string = '';
  cardHolder: string = '';
  expirationDate: string = '';
  cvv: string = '';

  constructor(private http: HttpClient) {}

  createPayPalPayment() {
    const body = {
      type: 'paypal',
      amount: this.total,
      params: [this.email, this.password]
    };

    this.http.post(`/payments/create/${this.clientId}`, body)
      .subscribe(() => {
        alert('Pago PayPal creado con éxito');
      });
  }

  createCreditCardPayment() {
    const body = {
      type: 'creditcard',
      amount: this.total,
      params: [this.cardNumber, this.cardHolder, this.expirationDate, this.cvv]
    };

    this.http.post(`/payments/create/${this.clientId}`, body)
      .subscribe(() => {
        alert('Pago con tarjeta creado con éxito');
      });
  }

  makePayment() {
    this.http.post(`/payments/pay/${this.clientId}`, {})
      .subscribe((resp: any) => {
        alert(resp);
        this.close.emit();
      });
  }
}
