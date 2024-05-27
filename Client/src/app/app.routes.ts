import { Routes } from '@angular/router';

export const routes: Routes = [
 {
    path: 'login',
    loadComponent: () => import('./components/login/login.component')
 },

 {
   path: 'home-analista',
   loadComponent: () => import('./components/home-analista/home-analista.component')
 },

 {
  path: 'crear-postulacion',
  loadComponent: () => import('./components/crear-postulacion/crear-postulacion.component')
 },

 {
  path: 'trabajadores',
  loadComponent: () => import('./components/trabajadores/trabajadores.component')
 },

 {
  path: 'ofertas-laborales',
  loadComponent: () => import('./components/ofertas-laborales/ofertas-laborales.component')
 }

];
