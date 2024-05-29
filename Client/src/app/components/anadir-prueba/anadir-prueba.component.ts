import { Component } from '@angular/core';
import HeaderPsicologoComponent from '../header-psicologo/header-psicologo.component';
import BarraHerramientasPsicologoComponent from '../barra-herramientas-psicologo/barra-herramientas-psicologo.component';

@Component({
  selector: 'app-anadir-prueba',
  standalone: true,
  imports: [HeaderPsicologoComponent, BarraHerramientasPsicologoComponent],
  templateUrl: './anadir-prueba.component.html',
  styleUrl: './anadir-prueba.component.css'
})
export default class AnadirPruebaComponent {
  postulante ={
    id: 16273672,
    name: 'Juan',
    titulo_oferta: 'Cocinero'
  };
}
