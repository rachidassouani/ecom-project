import { CategoryDTO } from "./category-dto";

export interface ProductRequest {
    id?: number
    name?: string;
    description?: string;
    price?: number;
    isAvailable?: boolean;
    categoryId?: number;
}