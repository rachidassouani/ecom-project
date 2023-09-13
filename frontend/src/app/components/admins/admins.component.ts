import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AdminDTO } from 'src/app/models/admin-dto';
import { UserDTO } from 'src/app/models/user-dto';
import { UserRegistrationRequest } from 'src/app/models/user-registration-request';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.scss']
})
export class AdminsComponent {

  sidebarVisible = false
  admins: AdminDTO[] = []
  adminToSave: UserRegistrationRequest = {}; 
  operation: 'create' | 'update' = 'create';
  errorMessage = ''

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private userService: UserService) {}

  ngOnInit() {    
    this.findAllAdmins();
  }

  private findAllAdmins() {
    this.userService.findAllAdmins().subscribe({
      next: (allAdmins) => {
        this.admins = allAdmins;
      },error: (error) => {
        console.log(error);
      }
    });
  }

  
  onSaveAdmin(userRegistrationRequest: UserRegistrationRequest) {
    if (userRegistrationRequest) {
      if (this.operation === 'create') {
        this.userService.saveAdmin(userRegistrationRequest)
        .subscribe({
          next: () => {

            // fetch the list of Admins in order to display our new saved Admin
            this.findAllAdmins();
            
            // to close sidebar from
            this.sidebarVisible = false

            // clear the Admin form
            this.adminToSave = {}

            // display success notification to the Admin
            this.messageService.add({
              severity: 'success',
              summary: 'Admin saved',
              detail: 'Admin saved successfully'
            })
          }, error: (err) => {
            if (err.error.statusCode == 401 || err.error.statusCode == 500) {
              this.errorMessage = err.error.message
            }
          }
        })
      }}
    }
  onDeleteAdmin(adminToDelete: AdminDTO | undefined) {
    this.confirmationService.confirm({
      header: 'Delete Admin',
      message: `Are you sure you want to delete ${adminToDelete?.email}, Please note this operation cannot be undone`,
      accept: () => {
        if(adminToDelete?.id !== null && adminToDelete?.id !== undefined) {
          this.userService.deleteAdmin(adminToDelete.id).subscribe({
            
            next: () => {
              this.findAllAdmins();
              
              // display success notification to the user
              this.messageService.add({
                severity: 'success',
                summary: 'Admin deleted',
                detail: 'Admin deleted successfully'
              })
            
            }, error: () => {

            }
          });
        }
      }
    });    
  }

  onCreateAdmin() {
    this.operation = 'create';
    this.adminToSave = {};
    this.sidebarVisible = true;
  }

  // cancel event received
  onCancel() {
    this.operation = 'create';
    this.adminToSave = {};
    this.sidebarVisible = false;
  }

  isAdminValid(): boolean {
    return this.isInputValid(this.adminToSave.firstName)
      && this.isInputValid(this.adminToSave.lastName)
      && this.isInputValid(this.adminToSave.email)
      && this.isInputValid(this.adminToSave.password)
  }

  private isInputValid(input: string | undefined): boolean {
    return input !== null && input !== undefined && input.length > 0
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
