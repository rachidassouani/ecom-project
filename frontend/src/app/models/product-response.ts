import { CategoryDTO } from "./category-dto";

export interface ProductResponse {
    id?: number
    name?: string;
    description?: string;
    price?: number;
    categoryDTO?: CategoryDTO;
    isAvailable?: boolean;
}