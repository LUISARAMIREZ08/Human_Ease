import { Component } from '@angular/core';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-novedades',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent],
  templateUrl: './novedades.component.html',
  styleUrl: './novedades.component.css'
})
export default class NovedadesComponent {

}
