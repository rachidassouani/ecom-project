import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CategoryDTO } from 'src/app/models/category-dto';
import { CategoryRequest } from 'src/app/models/category-request';
import { CategoryService } from 'src/app/services/category/category.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {

  constructor(private categoryService: CategoryService,
    private confirmationService: ConfirmationService,
    private userService: UserService,
    private messageService: MessageService) {}

  allCategories: CategoryDTO[] = []
  sidebarVisible = false
  category: CategoryRequest = {}
  selectedCategory!: CategoryDTO;
  operation: 'create' | 'update' = 'create';

  ngOnInit() {
    this.findAllCategories();
  }

  onDeleteCategory(categoryToDelete: CategoryDTO | undefined) {
    this.confirmationService.confirm({
      header: 'Delete Category',
      message: `Are you sure you want to delete ${categoryToDelete?.name}, Please note this operation cannot be undone`,
      accept: () => {
        if(categoryToDelete?.id !== null && categoryToDelete?.id !== undefined) {
          this.categoryService.deleteCategory(categoryToDelete.id).subscribe({
            
            next: () => {
              this.findAllCategories();
              
              // display success notification to the user
              this.messageService.add({
                severity: 'success',
                summary: 'Category deleted',
                detail: 'Category deleted successfully'
              })
            
            }, error: () => {

            }
          });
        }
      }
    });    
  }


  findAllCategories() {
    this.categoryService.findAllCategories().subscribe({
      next: (res) => {
        this.allCategories = res;
      }
    });
  }

  onCancel() {
    this.operation = 'create';
    this.category = {};
    this.sidebarVisible = false;
  }

  isCategoryValid(): boolean {
    return this.isInputValid(this.category.name)
      && this.isInputValid(this.category.description)
  }

  private isInputValid(input: string | undefined): boolean {
    return input !== null && input !== undefined && input.length > 0
  }

  onCreateCategory() {
    this.findAllCategories()
    this.operation = 'create';
    this.category = {};
    this.sidebarVisible = true;
  }


  onSaveCategory(categoryRequest: CategoryRequest) {
    if (categoryRequest) {
      if (this.operation === 'create') {
        this.categoryService.saveCategory(categoryRequest)
        .subscribe({
          next: () => {

            // fetch the list of categories in order to display our new saved category
            this.findAllCategories();
            
            // to close sidebar from
            this.sidebarVisible = false

            // clear the category form
            this.category = {}

            // display success notification to the user
            this.messageService.add({
              severity: 'success',
              summary: 'Category saved',
              detail: 'Category saved successfully'
            })
          }, error: () => {

          }
        })
      }}
    }

  isUserAdmin() {
    if (!this.userService.isUserAdmin()) {
      return false;
    }
    return true;
  }
}
