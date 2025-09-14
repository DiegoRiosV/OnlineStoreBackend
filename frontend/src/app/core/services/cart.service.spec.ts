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

  it('setQty sets exact quantity and removes when <=0', () => {
    service.add({ productId: 'p1', title: 'A', price: 10, imageUrl: 'x', qty: 1 });
    service.setQty('p1', 5);
    expect(service.snapshot[0].qty).toBe(5);
    expect(service.count).toBe(5);

    service.setQty('p1', 0);
    expect(service.count).toBe(0);
    expect(service.snapshot.length).toBe(0);
  });

  it('remove deletes the item', () => {
    service.add({ productId: 'p2', title: 'B', price: 20, imageUrl: 'y', qty: 2 });
    service.remove('p2');
    expect(service.count).toBe(0);
  });

  it('clear empties cart', () => {
    service.add({ productId: 'p1', title: 'A', price: 10, imageUrl: 'x', qty: 1 });
    service.add({ productId: 'p2', title: 'B', price: 20, imageUrl: 'y', qty: 2 });
    service.clear();
    expect(service.count).toBe(0);
    expect(service.snapshot).toEqual([]);
  });

});
