import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import HeaderUsuarioLogueadoComponent from '../header-usuario-logueado/header-usuario-logueado.component';

export interface Postulacion {
  fecha_postulacion: string;
  titulo_postulacion: string;
  cargo: string;
  link: string;
  estado: string;
  fecha: string;
}

const ELEMENT_DATA: Postulacion[] = [
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: 'https://www.google.com/', estado: 'Primera entrevista', fecha: '30/04/2024'},
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: 'https://www.google.com/', estado: 'Pruebas psicotecnicas', fecha: '30/04/2024'},
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: 'https://www.google.com/', estado: 'Primera entrevista', fecha: '30/04/2024'},
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: 'https://www.google.com/', estado: 'Primera entrevista', fecha: '30/04/2024'},
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: 'https://www.google.com/', estado: 'Primera entrevista', fecha: '30/04/2024'},
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: 'https://www.google.com/', estado: 'Primera entrevista', fecha: '30/04/2024'},
  {fecha_postulacion: '2021-10-01', titulo_postulacion: 'Programador backend spring boot', cargo: 'Programador Backend', link: '', estado: 'Cerrado', fecha: '30/04/2024'},
];

@Component({
  selector: 'app-procesos-activos',
  standalone: true,
  imports: [MatTableModule, CommonModule, HeaderUsuarioLogueadoComponent ],
  templateUrl: './procesos-activos.component.html',
  styleUrl: './procesos-activos.component.css'
})

export default class ProcesosActivosComponent {
  displayedColumns: string[] = ['fecha_postulacion', 'titulo_postulacion', 'cargo', 'estado', 'fecha'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
}
