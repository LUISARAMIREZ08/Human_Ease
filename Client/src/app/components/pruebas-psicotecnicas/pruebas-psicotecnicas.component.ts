import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import BarraHerramientasPsicologoComponent from '../barra-herramientas-psicologo/barra-herramientas-psicologo.component';
import HeaderPsicologoComponent from '../header-psicologo/header-psicologo.component';

export interface Postulante {
  id: number;
  name: string;
  titulo_oferta: string;
}

const ELEMENT_DATA: Postulante[] = [
  {id: 16273672, name: 'Juan', titulo_oferta: 'Cocinero'},
  {id: 227366351133, name: 'Pedro', titulo_oferta: 'Mesero'},
  {id: 327366351133, name: 'Maria', titulo_oferta: 'Cajero'},
  {id: 427366351133, name: 'Ana', titulo_oferta: 'Cocinero'},
  {id: 527366351133, name: 'Jose', titulo_oferta: 'Mesero'},
  {id: 627366351133, name: 'Luis', titulo_oferta: 'Cajero'},
  {id: 727366351133, name: 'Carlos', titulo_oferta: 'Cocinero'},
];

@Component({
  selector: 'app-pruebas-psicotecnicas',
  standalone: true,
  imports: [
    CommonModule, 
    MatFormFieldModule, 
    MatInputModule,
    BarraHerramientasPsicologoComponent, 
    HeaderPsicologoComponent
  ],
  templateUrl: './pruebas-psicotecnicas.component.html',
  styleUrls: ['./pruebas-psicotecnicas.component.css']
})
export default class PruebasPsicotecnicasComponent {
  dataSource = ELEMENT_DATA;
  filteredData = ELEMENT_DATA;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.filteredData = this.dataSource.filter(postulante => 
      postulante.name.toLowerCase().includes(filterValue) || 
      postulante.titulo_oferta.toLowerCase().includes(filterValue) ||
      postulante.id.toString().includes(filterValue)
    );
  }
}
