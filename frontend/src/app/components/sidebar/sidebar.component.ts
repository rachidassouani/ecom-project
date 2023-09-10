import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  constructor(private router: Router) {

  }

  //Array<MenuItem> 
  sideBarItems!: MenuItem[];


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
          this.router.navigate(['products'])
        }},  
      
        { label: 'Categories',
          icon: 'pi pi-users', 
          command: () => {
            this.router.navigate(['categories'])
        }}
    ];
  }

  onSideBarClicked() {
    this.router.navigate(['categories']);
  }
}


