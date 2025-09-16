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

export interface CartItem {
  productId: number;
  nameProduct: string;
  price: number;  // BigDecimal en back → number en front
  qty: number;
}

export interface AddItemRequest {
  cartId: number;
  productId: number;
  quantity: number;
}
