import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface Postulantes {
  fecha_postulacion: string;
  nombre: string;
  profesion: string;
}

const ELEMENT_DATA: Postulantes[] = [
  {fecha_postulacion: '2021-10-01', nombre: 'Juan Perez', profesion: 'Cocinero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Pedro Perez', profesion: 'Mesero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Maria Perez', profesion: 'Cajero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Juan Perez', profesion: 'Cocinero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Pedro Perez', profesion: 'Mesero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Maria Perez', profesion: 'Cajero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Juan Perez', profesion: 'Cocinero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Pedro Perez', profesion: 'Mesero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Maria Perez', profesion: 'Cajero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Juan Perez', profesion: 'Cocinero'},
  {fecha_postulacion: '2021-10-01', nombre: 'Pedro Perez', profesion: 'Mesero'}
];

@Component({
  selector: 'app-postulantes',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule],
  templateUrl: './postulantes.component.html',
  styleUrl: './postulantes.component.css'
})
export default class PostulantesComponent {

  displayedColumns: string[] = ['fecha_postulacion', 'nombre', 'profesion', 'curriculum', 'proceso'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  viewProfile(element: any): void{
    console.log(element);
  }

  viewProcess(element: any): void{
    console.log(element);
  }

}
