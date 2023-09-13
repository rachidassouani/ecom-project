import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthenticationResponse } from 'src/app/models/AuthenticationResponse';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent {

  constructor(private router: Router) {

  }

  items!: MenuItem[];

  ngOnInit() {
    this.items = [
      {label: 'Profile', icon:'pi pi-user'},
      {label: 'Settings', icon:'pi pi-cog'},
      {separator: true},
      {
        label: 'Sign out', 
        icon:'pi pi-sign-out' , 
        command: () => {
          localStorage.clear();
          this.router.navigate(['login']);
      }}
    ];
  }

  getUsername(): string {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      if (authResponse && authResponse.userDTO && authResponse.userDTO.email) {
        return authResponse.userDTO.email;
      }
    }
    return 'No User Logged in';
  }

  getUserRole(): string | string[] {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      if (authResponse && authResponse.userDTO && authResponse.userDTO.roles) {
        return authResponse.userDTO.roles;
      }
    }
    return ' ';
  }
}
