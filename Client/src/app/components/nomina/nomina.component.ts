import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface Periodo {
  ano: string;
  mes: string;
  periodo: string;
  fecha_inicial: string;
  fecha_final: string;
  dias: string;
  horas: string;
}

export interface Trabajador {
  id: number;
  name: string;
  empleado: string;
  centro_costo: string;
}

const ELEMENT_DATA: Periodo[] = [
  {ano: '2024', mes: '8', periodo: '1', fecha_inicial: '2024-08-01', fecha_final: '2024-08-15', dias: '15', horas: '120'},
  {ano: '2024', mes: '8', periodo: '2', fecha_inicial: '2024-08-16', fecha_final: '2024-08-31', dias: '16', horas: '128'},
  {ano: '2024', mes: '9', periodo: '1', fecha_inicial: '2024-09-01', fecha_final: '2024-09-15', dias: '15', horas: '120'},
  {ano: '2024', mes: '9', periodo: '2', fecha_inicial: '2024-09-16', fecha_final: '2024-09-30', dias: '15', horas: '120'},
  {ano: '2024', mes: '10', periodo: '1', fecha_inicial: '2024-10-01', fecha_final: '2024-10-15', dias: '15', horas: '120'},
  {ano: '2024', mes: '10', periodo: '2', fecha_inicial: '2024-10-16', fecha_final: '2024-10-31', dias: '16', horas: '128'},
  {ano: '2024', mes: '11', periodo: '1', fecha_inicial: '2024-11-01', fecha_final: '2024-11-15', dias: '15', horas: '120'},
  {ano: '2024', mes: '11', periodo: '2', fecha_inicial: '2024-11-16', fecha_final: '2024-11-30', dias: '15', horas: '120'},
];

const ELEMENT_DATA2: Trabajador[] = [
  {id: 16273672, name: 'Juan Mendez' , empleado: 'Cocinero', centro_costo: 'Cocina'},
  {id: 227366351133, name: 'Pedro Ramirez', empleado: 'Mesero', centro_costo: 'Salon'},
  {id: 327366351133, name: 'Maria Gonzalez', empleado: 'Cajero', centro_costo: 'Caja'},
  {id: 427366351133, name: 'Ana Martinez', empleado: 'Cocinero', centro_costo: 'Cocina'},
  {id: 527366351133, name: 'Jose Lopez', empleado: 'Mesero', centro_costo: 'Salon'},
  {id: 627366351133, name: 'Luis Rodriguez', empleado: 'Cajero', centro_costo: 'Caja'},
  {id: 727366351133, name: 'Carlos Hernandez', empleado: 'Cocinero', centro_costo: 'Cocina'},
  {id: 827366351133, name: 'Rosa Gutierrez', empleado: 'Mesero', centro_costo: 'Salon'},
];

@Component({
  selector: 'app-nomina',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule, CommonModule],
  templateUrl: './nomina.component.html',
  styleUrls: ['./nomina.component.css']
})
export default class NominaComponent {
  option: string = 'periodos';
  dataVisible: boolean = false;
  formGenerated: boolean = false;
  nominaLiquidada: boolean = false;

  displayedColumns: string[] = ['ano', 'mes', 'periodo', 'fecha_inicial', 'fecha_final', 'dias', 'horas', 'accion'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  displayedColumns2: string[] = ['name', 'empleado', 'id', 'centro_costo'];
  dataSource2 = new MatTableDataSource(ELEMENT_DATA2);
  selectedRow: Trabajador | null = null;

  fechaInicial: string = '';
  fechaFinal: string = '';


  changeOption(option: string): void {
    this.option = option;
  }

  applyFilter(event: Event, table: string) {
    const filterValue = (event.target as HTMLInputElement).value;
    if (table === 'periodos') {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    } else if (table === 'trabajadores') {
      this.dataSource2.filter = filterValue.trim().toLowerCase();
    }
  }

  selectRow(row: Trabajador) {
    if (this.selectedRow === row) {
      this.selectedRow = null; // Deseleccionar la fila si ya está seleccionada
    } else {
      this.selectedRow = row; // Seleccionar una nueva fila
    }
  }

  deletePeriodo(element: any): void {
    console.log(element);
  }


  updateFechaInicial(event: Event) {
    this.fechaInicial = (event.target as HTMLInputElement).value;
  }

  updateFechaFinal(event: Event) {
    this.fechaFinal = (event.target as HTMLInputElement).value;
  }

  mostrarDatos(): void {
    console.log(this.fechaInicial);
    console.log(this.fechaFinal);
    this.dataVisible = true;
  }

  liquidarNomina(): void {
    console.log('Liquidar nomina');
    this.nominaLiquidada = true;
    
  }

  generarInforme(): void {
    console.log('Generar informe');
    this.formGenerated = true;
  }

  isDateValid(): boolean {
    return !!this.fechaInicial && !!this.fechaFinal;
  }

  isFormValid(): boolean {
    if (this.fechaFinal === '' || this.fechaInicial === '' || this.selectedRow === null) {
      console.log('Formulario inválido');
      return false;
    } else {
      console.log('Formulario válido');
      return true;
    }
  }

  verNominaLiquidada(event: Event): void {
    event.preventDefault();
    const url = `liquidacion-empleado?fecha-inicial=${this.fechaInicial}&fecha-final=${this.fechaFinal}&id=${this.selectedRow?.id}`;
    window.location.href = url;
  }
}
