import { TestBed } from '@angular/core/testing';
import { CartService } from './cart.service';

describe('CartService v1', () => {
  let service: CartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CartService);
  });

  it('starts with 0 items', () => {
    expect(service.count).toBe(0);
    expect(service.snapshot).toEqual([]);
  });

  it('increments count when adding an item', () => {
    service.add({ productId: 'p1', title: 'A', price: 10, imageUrl: 'x', qty: 1 });
    expect(service.count).toBe(1);

    service.add({ productId: 'p1', title: 'A', price: 10, imageUrl: 'x', qty: 1 });
    expect(service.count).toBe(2);
    expect(service.snapshot[0].qty).toBe(2);
  });
});
