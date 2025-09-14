export interface Product {
  id: string | number;
  title: string;
  price: number;
  imageUrl: string;
  color?: string;
  description?: string;
}

export interface CartItem {
  productId: Product['id'];
  title: string;
  price: number;
  imageUrl: string;
  qty: number;
}
