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
 }
];
