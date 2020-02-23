import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
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
    ProductListComponent,
    ProductCategoryMenuComponent,
    SearchComponent,
    ProductDetailsComponent,
    ProfileComponent,
    // OktaCallbackComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    NgbModule.forRoot(),
    HttpClientModule,
    OktaAuthModule.initAuth(config)
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, OktaAuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }


