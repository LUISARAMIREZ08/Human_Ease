import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface liquidacion_empleado {
  con: string;
  descripcion: string;
  cedula: string;
  cantidad: string;
  devengados: string;
  deducidos: string;
  fecha_liquidacion: string;
  fecha_pago: string;
}

const ELEMENT_DATA: liquidacion_empleado[] = [
  {con: '1', descripcion: 'Salario', cedula: '123456789', cantidad: '1', devengados: '1000000', deducidos: '0', fecha_liquidacion: '2024-08-01', fecha_pago: '2024-08-15'},
  {con: '2', descripcion: 'Auxilio de transporte', cedula: '123456789', cantidad: '1', devengados: '106454', deducidos: '0', fecha_liquidacion: '2024-08-01', fecha_pago: '2024-08-15'},
  {con: '3', descripcion: 'Horas extras', cedula: '123456789', cantidad: '10', devengados: '100000', deducidos: '0', fecha_liquidacion: '2024-08-01', fecha_pago: '2024-08-15'},
  {con: '4', descripcion: 'Bonificacion', cedula: '123456789', cantidad: '1', devengados: '100000', deducidos: '0', fecha_liquidacion: '2024-08-01', fecha_pago: '2024-08-15'},
  {con: '5', descripcion: 'Salud', cedula: '123456789', cantidad: '1', devengados: '0', deducidos: '100000', fecha_liquidacion: '2024-08-01', fecha_pago: '2024-08-15'},
];

@Component({
  selector: 'app-liquidacion-empleado',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatTableModule],
  templateUrl: './liquidacion-empleado.component.html',
  styleUrl: './liquidacion-empleado.component.css'
})
export default class LiquidacionEmpleadoComponent {

  displayedColumns: string[] = ['con', 'descripcion', 'cedula', 'cantidad', 'devengados', 'deducidos', 'fecha_liquidacion', 'fecha_pago'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
}
