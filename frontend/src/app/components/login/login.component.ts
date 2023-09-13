import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/models/AuthenticationRequest';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {
  
  authenticationRequest: AuthenticationRequest = {};
  errorMessage: string = '';

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
    ) {  }

  login() {
    
    this.authenticationService.login(this.authenticationRequest).subscribe({
        next: (resData) => {
          console.log("resData" + resData);
          localStorage.setItem('user', JSON.stringify(resData));
          this.router.navigate(['products']);
        }, error: (e) => {
          console.log(e);
          
          if (e.error.statusCode == 401) {
            this.errorMessage = e.error.message
          }
        }
      });
  }

  onRegister() {
    this.router.navigate(['register']);
  }
}
