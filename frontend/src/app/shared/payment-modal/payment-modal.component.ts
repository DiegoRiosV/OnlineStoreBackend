import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-payment-modal',
  standalone: true,
  templateUrl: './payment-modal.component.html',
  styleUrls: ['./payment-modal.component.css']
})
export class PaymentModalComponent {
  @Output() closed = new EventEmitter<void>();
  @Output() selected = new EventEmitter<string>();

  close() {
    this.closed.emit();
  }

  pay(method: string) {
    this.selected.emit(method);
  }
}
