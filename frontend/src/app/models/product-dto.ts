import { CategoryDTO } from "./category-dto";

export interface ProductDTO {
    id?: number
    name?: string;
    description?: string;
    price?: number;
    categoryDTO?: CategoryDTO;
    isAvailable?: boolean;
}