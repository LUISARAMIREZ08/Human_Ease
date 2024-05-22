import { Component } from '@angular/core';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-home-analista',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent],
  templateUrl: './home-analista.component.html',
  styleUrl: './home-analista.component.css'
})
export default class HomeAnalistaComponent {

}
