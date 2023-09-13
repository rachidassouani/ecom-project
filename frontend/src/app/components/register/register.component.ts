import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/models/AuthenticationRequest';
import { UserRegistrationRequest } from 'src/app/models/user-registration-request';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent {

  errorMessage = ''
  userRegistartionRequest: UserRegistrationRequest = {};

  constructor(
    private router: Router, 
    private userService: UserService,
    private authenticationService: AuthenticationService) {}

  onLogin() {
    this.router.navigate(['login'])
  }

  onRegisterAccount() {
    this.userService.saveUser(this.userRegistartionRequest)
      .subscribe({
        next: () => {
          const authRequest: AuthenticationRequest = {
            email: this.userRegistartionRequest.email,
            password: this.userRegistartionRequest.password
          }
          this.authenticationService.login(authRequest)
            .subscribe({
              next: (res) => {
                localStorage.setItem('user', JSON.stringify(res));
                this.router.navigate(['products'])
              
              }, error: (e) => {
               
              }
            })
        }, error: (err) => {
          if (err.error.statusCode == 401 || err.error.statusCode == 500) {
            this.errorMessage = err.error.message
          }
        }
      });
  }
 }
