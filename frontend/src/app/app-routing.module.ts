import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProductsComponent } from './components/products/products.component';
import { RegisterComponent } from './components/register/register.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { AdminsComponent } from './components/admins/admins.component';
import { AccessGuardService } from './services/guard/access-guard.guard';
import { AdminGuardService } from './services/guard/admin.guard';
import { ForbidenComponent } from './components/forbiden/forbiden.component';

const routes: Routes = [
  {path:'', redirectTo: 'products', pathMatch: 'full'},
  {path:'products', component: ProductsComponent},
  {path:'categories', component: CategoriesComponent, canActivate: [AdminGuardService]},
  {path:'login', component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'admins', component: AdminsComponent, canActivate: [AdminGuardService]},
  {path:'forbiden', component: ForbidenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
