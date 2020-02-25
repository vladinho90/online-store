import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
<<<<<<< HEAD
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NotFoundComponent } from './components/error/not-found/not-found.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { AdminComponent } from './components/user/admin/admin.component';
import { DetailComponent } from './components/user/detail/detail.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { CategoryMenuComponent } from './components/product/category-menu/category-menu.component';
import { SearchComponent } from './components/product/search/search/search.component';
import { ProductDetailsComponent } from './components/product/product-details/product-details.component';

=======
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ProductService } from './services/product.service';
import { Routes, RouterModule } from '@angular/router';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { SearchComponent } from './components/search/search.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { OktaAuthModule, OktaCallbackComponent, OktaAuthGuard } from '@okta/okta-angular';
import { AuthInterceptor } from './auth.interceptor';
import { ProfileComponent } from './components/profile/profile.component';



const routes: Routes = [ //specific to generic
  {
    path: 'implicit/callback',
    component: OktaCallbackComponent
  },

  { path: 'search/:keyword', component: ProductListComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'user/:username', component: ProfileComponent, canActivate: [OktaAuthGuard] },
  { path: 'category/:name', component: ProductListComponent },
  { path: 'category/', component: ProductListComponent },
  { path: 'products', component: ProductListComponent },
  // { path: '', redirectTo: '/products', pathMatch: 'full' },
  // { path: '**', redirectTo: '/products', pathMatch: 'full' }
]
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7

const config = {
  issuer: 'https://dev-789180.okta.com/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oa2gt9amo6h0FqZV4x6',
  pkce: true,
  scopes: ['openid', 'profile', 'email', 'groups', 'address', 'phone']
}
@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    AdminComponent,
    DetailComponent,
    ProductListComponent,
    CategoryMenuComponent,
    SearchComponent,
    ProductDetailsComponent,
<<<<<<< HEAD
   
=======
    ProfileComponent,
    // OktaCallbackComponent
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
  ],
  imports: [
    BrowserModule,
<<<<<<< HEAD
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
=======
    NgbModule.forRoot(),
    HttpClientModule,
    OktaAuthModule.initAuth(config)
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, OktaAuthGuard],
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
  bootstrap: [AppComponent]
})
export class AppModule { }


