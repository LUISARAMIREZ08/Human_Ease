import { Component } from '@angular/core';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-crear-postulacion',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent],
  templateUrl: './crear-postulacion.component.html',
  styleUrl: './crear-postulacion.component.css'
})
export default class CrearPostulacionComponent {

}
