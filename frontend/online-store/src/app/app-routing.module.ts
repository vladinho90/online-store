import { AuthGuard } from './guards/auth.guard';
import { NgModule } from '@angular/core';
import { Router, Routes, RouterModule } from '@angular/router';
import { Role } from './models/user/role.enum';
import { NotFoundComponent } from './components/error/not-found/not-found.component';
import { UnathorizedComponent } from './components/error/unathorized/unathorized.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { AdminComponent } from './components/user/admin/admin.component';
import { DetailComponent } from './components/user/detail/detail.component';
import { CategoryMenuComponent } from './components/product/category-menu/category-menu.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ProductDetailsComponent } from './components/product/product-details/product-details.component';



const routes: Routes = [
  //public page
  // { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'search/:keyword', component: ProductListComponent },
  { path: 'category/:name', component: ProductListComponent },
  { path: 'category/', component: ProductListComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  //user+admin
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER, Role.ADMIN] }
  },
  {
    path: 'detail/:id',
    component: DetailComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.ADMIN] }
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.ADMIN] }
  },
  //public error pages
  { path: '404', component: NotFoundComponent},
  { path: '401', component: UnathorizedComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(private router: Router) {
    //for unknown pages
    // this.router.errorHandler = (error: any) => {
    //   this.router.navigate(['/404']);
    // };
  }
}
