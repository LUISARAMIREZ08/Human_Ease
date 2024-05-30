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
  path: 'postulaciones',
  loadComponent: () => import('./components/postulaciones/postulaciones.component')
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
  path: 'novedades',
  loadComponent: () => import('./components/novedades/novedades.component')
 },

 {
  path: 'postulantes',
  loadComponent: () => import('./components/postulantes/postulantes.component')
 },
 
 {
  path: 'ofertas-laborales',
  loadComponent: () => import('./components/ofertas-laborales/ofertas-laborales.component')
 },

 {
  path: 'usuario-cuenta',
  loadComponent: () => import('./components/usuario-cuenta/usuario-cuenta.component')
 },

 {
  path: 'procesos-activos',
  loadComponent: () => import('./components/procesos-activos/procesos-activos.component')
 },

 {
  path: 'crear-cargo',
  loadComponent: () => import('./components/crear-cargo/crear-cargo.component')
 },

 {
  path: 'aporte-seguridad-social',
  loadComponent: () => import('./components/aporte-seguridad-social/aporte-seguridad-social.component')
 },

 {
  path: 'nomina',
  loadComponent: () => import('./components/nomina/nomina.component')
 },

 {
  path: 'liquidacion-empleado',
  loadComponent: () => import('./components/liquidacion-empleado/liquidacion-empleado.component')
 },

 {
  path: 'crear-periodo',
  loadComponent: () => import('./components/crear-periodo/crear-periodo.component')
 },

 {
  path: 'home-psicologo',
  loadComponent: () => import('./components/home-psicologo/home-psicologo.component')
 },

 {
  path: 'pruebas-psicotecnicas',
  loadComponent: () => import('./components/pruebas-psicotecnicas/pruebas-psicotecnicas.component')
 },

 {
  path: 'anadir-prueba',
  loadComponent: () => import('./components/anadir-prueba/anadir-prueba.component')
 },
 
 {
  path: 'resultados-pruebas',
  loadComponent: () => import ('./components/resultados-pruebas/resultados-pruebas.component')
 },

 {
   path: 'proceso-contratacion',
   loadComponent: () => import('./components/proceso-contratacion/proceso-contratacion.component')
 },

 {
   path: 'ver-perfil',
   loadComponent: () => import('./components/ver-perfil/ver-perfil.component')
 }
 
];
