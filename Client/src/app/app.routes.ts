import { Routes } from '@angular/router';

export const routes: Routes = [
{
   path: '',
   redirectTo: 'login',
   pathMatch: 'full'
},

 {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)
 },

 {
    path: 'register',
    loadComponent: () => import('./components/register/register.component')
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
 }

];
