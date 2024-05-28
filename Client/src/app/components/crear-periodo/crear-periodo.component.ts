import { Component } from '@angular/core';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-crear-periodo',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent],
  templateUrl: './crear-periodo.component.html',
  styleUrl: './crear-periodo.component.css'
})
export default class CrearPeriodoComponent {

}
