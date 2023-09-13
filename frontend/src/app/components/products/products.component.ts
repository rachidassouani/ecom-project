import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AuthenticationResponse } from 'src/app/models/AuthenticationResponse';
import { CategoryDTO } from 'src/app/models/category-dto';
import { ProductDTO } from 'src/app/models/product-dto';
import { ProductRequest } from 'src/app/models/product-request';
import { CategoryService } from 'src/app/services/category/category.service';
import { ProductService } from 'src/app/services/product/product.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {

  sidebarVisible = false
  allProducts: ProductDTO[] = []
  product: ProductRequest = {};
  categories: CategoryDTO[] = []
  selectedCategory!: CategoryDTO;
  operation: 'create' | 'update' = 'create';
  categoryId!: number;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private productService: ProductService,
    private router: Router,
    private userService: UserService,
    private categoryService: CategoryService) {}

  ngOnInit() {    
    this.findAllProducts();
  }

  private findAllProducts() {
    this.productService.findAllProducts().subscribe({
      next: (allProducts) => {
        this.allProducts = allProducts;
      },error: (error) => {
      }
    });
  }

  
  onSaveProduct(productRequest: ProductRequest) {
    if (productRequest) {
      if (this.operation === 'create') {
        productRequest.categoryId = this.selectedCategory.id;
        this.productService.saveProduct(productRequest)
        .subscribe({
          next: () => {

            // fetch the list of products in order to display our new saved product
            this.findAllProducts();
            
            // to close sidebar from
            this.sidebarVisible = false

            // clear the product form
            this.product = {}

            // display success notification to the user
            this.messageService.add({
              severity: 'success',
              summary: 'Product saved',
              detail: 'Product saved successfully'
            })
          }, error: () => {

          }
        })
      }}
    }
  onDeleteProduct(productToDelete: ProductDTO | undefined) {
    this.confirmationService.confirm({
      header: 'Delete Product',
      message: `Are you sure you want to delete ${productToDelete?.name}, Please note this operation cannot be undone`,
      accept: () => {
        if(productToDelete?.id !== null && productToDelete?.id !== undefined) {
          this.productService.deleteProduct(productToDelete.id).subscribe({
            
            next: () => {
              this.findAllProducts();
              
              // display success notification to the user
              this.messageService.add({
                severity: 'success',
                summary: 'Product deleted',
                detail: 'Product deleted successfully'
              })
            
            }, error: () => {

            }
          });
        }
      }
    });    
  }

  onCreateProduct() {
    this.findAllCategories()
    this.operation = 'create';
    this.product = {};
    this.sidebarVisible = true;
  }

  // cancel event received
  onCancel() {
    this.operation = 'create';
    this.product = {};
    this.sidebarVisible = false;
  }

  isProductValid(): boolean {
    return this.isInputValid(this.product.name)
      && this.isInputValid(this.product.description)
      && this.product.price != undefined && this.product.price > 0
  }

  private isInputValid(input: string | undefined): boolean {
    return input !== null && input !== undefined && input.length > 0
  }
  

  private findAllCategories(){
    this.categoryService.findAllCategories().subscribe({
      next: (resData) => {
        this.categories = resData;
      }
      ,error: (error) => {
        console.log(error);
      }
    })
  }
  
  handleSelectedCategoryId(categoryId: any) {
    this.categoryId =categoryId;
    this.productService.findAllProductsByCategoryId(categoryId).subscribe({
      next: (resData) => {
        this.allProducts = resData;
      }, error: (error) => {
        console.log(error);
      }
    })
  }

  handleLoadAllProducts() {
    this.findAllProducts();
  }

  onAddToCart() {
    if (!this.isUserLogged()) {
      this.router.navigate(['login'])
    }
  }

  private isUserLogged() {
    if (!this.userService.isUserLogged()) {
      return false;
    }
    return true;
  }

  isUserAdmin() {
    if (!this.userService.isUserAdmin()) {
      return false;
    }
    return true;
  }
}