import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface Novedades {
  fecha: string;
  cedula: string;
  nombre: string;
  concepto: string;
  valor: number;
  descripcion: string;
  centro_costo: string;
}

const ELEMENT_DATA: Novedades[] = [
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Juan Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Pedro Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Maria Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Juan Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Pedro Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Maria Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Juan Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Pedro Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
  {fecha: '2021-10-01', cedula: '123456789', nombre: 'Maria Perez', concepto: 'Nomina', valor: 1000000, descripcion: 'Pago de nomina', centro_costo: 'Administracion'},
];
@Component({
  selector: 'app-novedades',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatFormFieldModule, MatTableModule],
  templateUrl: './novedades.component.html',
  styleUrl: './novedades.component.css'
})
export default class NovedadesComponent {

  displayedColumns: string[] = ['fecha', 'cedula', 'nombre', 'concepto', 'valor', 'descripcion', 'centro_costo'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);


}
