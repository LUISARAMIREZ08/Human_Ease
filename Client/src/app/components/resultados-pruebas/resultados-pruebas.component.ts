import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import HeaderPsicologoComponent from '../header-psicologo/header-psicologo.component';
import BarraHerramientasPsicologoComponent from '../barra-herramientas-psicologo/barra-herramientas-psicologo.component';

export interface Prueba {
  tipo_prueba: string;
  resultados: string;
  observaciones: string;
}

const ELEMENT_DATA: Prueba[] = [
  {tipo_prueba: 'Prueba de habilidades', resultados: 'Aprobado', observaciones: 'Ninguna'},
  {tipo_prueba: 'Prueba de conocimientos', resultados: 'Aprobado', observaciones: 'Ninguna'},
  {tipo_prueba: 'Prueba de personalidad', resultados: 'Aprobado', observaciones: 'Ninguna'},
  {tipo_prueba: 'Prueba de aptitudes', resultados: 'Aprobado', observaciones: 'Ninguna'},
  {tipo_prueba: 'Prueba de intereses', resultados: 'Aprobado', observaciones: 'Ninguna'},
  {tipo_prueba: 'Prueba de valores', resultados: 'Aprobado', observaciones: 'Ninguna'},
  {tipo_prueba: 'Prueba de motivaciones', resultados: 'Aprobado', observaciones: 'Ninguna'},
];

@Component({
  selector: 'app-resultados-pruebas',
  standalone: true,
  imports: [CommonModule, HeaderPsicologoComponent, BarraHerramientasPsicologoComponent],
  templateUrl: './resultados-pruebas.component.html',
  styleUrl: './resultados-pruebas.component.css'
})
export default class ResultadosPruebasComponent {
  dataSource = ELEMENT_DATA;

  postulante ={
    id: 16273672,
    name: 'Juan',
    titulo_oferta: 'Cocinero'
  };
}
