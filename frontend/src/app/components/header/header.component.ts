import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

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
    return 'raachidassouani';
  }

  getUserRole(): string {
    return 'ADMIN';
  }
}
