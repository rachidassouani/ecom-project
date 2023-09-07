import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  sideBarItems: Array<MenuItem> = [
    {label: 'Home', icon: 'pi pi-home'},
    {label: 'Products', icon: 'pi pi-users'}
  ]
}
