import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent {

  errorMessage = ''
  //customerRegistartionRequest: CustomerRegistartionRequest = {};

  constructor(
    private router: Router, 
    private productService: ProductService,
    private authenticationService: AuthenticationService) {}

  onLogin() {
    this.router.navigate(['login'])
  }

  onRegisterAccount() {
//     this.customerService.saveCustomer(this.customerRegistartionRequest)
//       .subscribe({
//         next: () => {
//           const authRequest: AuthenticationRequest = {
//             email: this.customerRegistartionRequest.email,
//             password: this.customerRegistartionRequest.password
//           }
//           this.authenticationService.login(authRequest)
//             .subscribe({
//               next: (res) => {
//                 localStorage.setItem('user', JSON.stringify(res));
//                 this.router.navigate(['customers'])
              
//               }, error: (e) => {
//                 if (e.error.statusCode == 401) {
//                   this.errorMessage = e.error.message
//                 }
//               }
//             })
//         }
//       });
//   }
 }

}
