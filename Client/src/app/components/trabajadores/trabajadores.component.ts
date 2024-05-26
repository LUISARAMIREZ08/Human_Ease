import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface Trabajador {
  id: number;
  name: string;
  last_name: string;
  role_person: string;
  salary_base: number;
  phone: string;
}

const ELEMENT_DATA: Trabajador[] = [
  {id: 1, name: 'Juan', last_name: 'Perez', role_person: 'Cocinero', salary_base: 1000, phone: '123456789'},
  {id: 2, name: 'Pedro', last_name: 'Ramirez', role_person: 'Mesero', salary_base: 800, phone: '123456789'},
  {id: 3, name: 'Maria', last_name: 'Gonzalez', role_person: 'Cajero', salary_base: 900, phone: '123456789'},
  {id: 4, name: 'Ana', last_name: 'Martinez', role_person: 'Cocinero', salary_base: 1000, phone: '123456789'},
  {id: 5, name: 'Jose', last_name: 'Lopez', role_person: 'Mesero', salary_base: 800, phone: '123456789'},
  {id: 6, name: 'Luis', last_name: 'Rodriguez', role_person: 'Cajero', salary_base: 900, phone: '123456789'},
  {id: 7, name: 'Carlos', last_name: 'Hernandez', role_person: 'Cocinero', salary_base: 1000, phone: '123456789'},
  {id: 8, name: 'Rosa', last_name: 'Gutierrez', role_person: 'Mesero', salary_base: 800, phone: '123456789'},
  {id: 9, name: 'Sofia', last_name: 'Diaz', role_person: 'Cajero', salary_base: 900, phone: '123456789'},
  {id: 10, name: 'Fernando', last_name: 'Sanchez', role_person: 'Cocinero', salary_base: 1000, phone: '123456789'},
  {id: 11, name: 'Luisa', last_name: 'Perez', role_person: 'Mesero', salary_base: 800, phone: '123456789'},
  {id: 12, name: 'Jorge', last_name: 'Ramirez', role_person: 'Cajero', salary_base: 900, phone: '123456789'},
];

@Component({
  selector: 'app-trabajadores',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule],
  templateUrl: './trabajadores.component.html',
  styleUrl: './trabajadores.component.css'
})
export default class TrabajadoresComponent {
  displayedColumns: string[] = ['id', 'name', 'last_name', 'role_person', 'salary_base', 'phone'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  clickedRows = new Set<Trabajador>();

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}


