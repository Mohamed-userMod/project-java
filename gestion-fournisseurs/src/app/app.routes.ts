import { Routes, provideRouter, withDebugTracing } from '@angular/router';
import { FournisseursComponent } from './fournisseurs/fournisseurs.component';
import { ProductsComponent } from './products/products.component';
import { GestionCommandesComponent } from './orders/orders.component';

import { ApplicationConfig, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

export const routes: Routes = [
  { path: 'fournisseurs', component: FournisseursComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'manage', component: GestionCommandesComponent },
];

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes, withDebugTracing())],
};
