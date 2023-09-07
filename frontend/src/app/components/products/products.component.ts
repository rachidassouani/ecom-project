import { Component, Input, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ProductDTO } from 'src/app/models/product-dto';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {

  sidebarVisible = false
  allProducts: ProductDTO[] = []
  customer = {};

  operation: 'create' | 'update' = 'create';

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private productService: ProductService) {}

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

  save() {
    
  }

  deleteCustomer(productDTO: ProductDTO) {
    this.confirmationService.confirm({
      header: 'Delete Product',
      message: `Are you sure you want to delete ${productDTO.name}, Please note this operation cannot be undone`,
      accept: () => {
        if(productDTO.id !== null && productDTO.id !== undefined) {
          this.productService.deleteProduct(productDTO.id).subscribe({
            
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


  // updateCustomer(customerToUpdate: CustomerDTO) {
  //   this.operation = 'update';
  //   this.sidebarVisible = true;
  //   this.customer = customerToUpdate;
  // }

  onCreateProduct() {
    this.operation = 'create';
    this.customer = {};
    this.sidebarVisible = true;
  }

  // cancel event received
  cancel() {
    this.operation = 'create';
    this.customer = {};
    this.sidebarVisible = false;
  }

  onUpdateProduct() {

  }

  onDeleteProduct() {
    
  }
}
