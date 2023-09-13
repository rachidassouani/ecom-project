import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { CategoryDTO } from 'src/app/models/category-dto';
import { ProductDTO } from 'src/app/models/product-dto';
import { CategoryService } from 'src/app/services/category/category.service';
import { ProductService } from 'src/app/services/product/product.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  constructor(private router: Router,
    private categoryService: CategoryService,
    private userService: UserService,
    private productService: ProductService) {}

  sideBarItems!: MenuItem[];
  allCategories: CategoryDTO[] = [];
  
  @Output() 
  categoryId: EventEmitter<number> = new EventEmitter();

  @Output()
  loadAllProducts: EventEmitter<void> = new EventEmitter();
  
  selectedCategoryId!: number;
  
  allProductsByCategoryId: ProductDTO[] = [];

  ngOnInit() {
    this.sideBarItems = [
    
      { label: 'Home', 
        icon: 'pi pi-home', 
        command: () => {
          console.log("hello2");
          this.router.navigate(['products'])
        }},
      
      { label: 'Products', 
        icon: 'pi pi-users',
        command: () => {
          this.loadAllProducts.emit();
          this.router.navigate(['products']);
        }}
    ];

    if (this.isUserAdmin()) {
      this.sideBarItems.push(
        { label: 'Categories',  icon: 'pi pi-users', command: () => {
          this.router.navigate(['categories'])}
        });
    }
    this.findAllCategories();
  }

  onSideBarClicked() {
    this.router.navigate(['categories']);
  }

  findAllCategories() {
    this.categoryService.findAllCategories().subscribe({
      next: (resData) => {
        this.allCategories = resData;
      }, error: (error) => {
        console.log(error);
      }
    });
  }

  onCategorySelect(event: any) {
    this.selectedCategoryId = event
    this.categoryId.emit(this.selectedCategoryId);
  }

  

  findProductsByCategory(categoryId: number) {
    this.productService.findAllProductsByCategoryId(categoryId).subscribe({
      next: (resData) => {
        this.allProductsByCategoryId = resData;
      }, error: (err) => {
        console.log(err);
      }
    });
  }

  isUserAdmin() {
    if (!this.userService.isUserAdmin()) {
      return false;
    }
    return true;
  }

}


