// core/models/product.model.ts
export interface Product {
  id: number;               // tu back devuelve Long -> number en TS
  idProduct: string;
  nameProduct: string;

  price: number;
  stock: number;

  imageUrl: string;
  description?: string;
  color?: string;
}

// OJO: CartItem usa nameProduct (no "title")
export interface CartItem {
  productId: Product['id'];
  nameProduct: string;
  price: number;
  imageUrl: string;
  qty: number;
}
