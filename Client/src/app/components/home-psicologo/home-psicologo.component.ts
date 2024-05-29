import { Component } from '@angular/core';
import HeaderPsicologoComponent from '../header-psicologo/header-psicologo.component';
import BarraHerramientasPsicologoComponent from '../barra-herramientas-psicologo/barra-herramientas-psicologo.component';

@Component({
  selector: 'app-home-psicologo',
  standalone: true,
  imports: [HeaderPsicologoComponent, BarraHerramientasPsicologoComponent],
  templateUrl: './home-psicologo.component.html',
  styleUrl: './home-psicologo.component.css'
})
export default class HomePsicologoComponent {

}
