import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';
import { ApiService } from '../../services/api.service';

export interface Trabajador {
  id: number;
  name: string;
  last_name: string;
  role_person: string;
  salary_base: number;
  phone: string;
}

@Component({
  selector: 'app-trabajadores',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule],
  templateUrl: './trabajadores.component.html',
  styleUrl: './trabajadores.component.css'
})
export default class TrabajadoresComponent implements OnInit{
  constructor(private api: ApiService) { }
  displayedColumns: string[] = ['id', 'name', 'last_name', 'role_person', 'salary_base', 'phone'];
  data:any;
  dataSource:any;
  clickedRows = new Set<Trabajador>();

  ngOnInit(): void {
    if (typeof localStorage !== 'undefined') { // Esto fue añadido por localstorage indefinidio en el navegador
      this.api.getAPI('employee/all').subscribe((data: Trabajador[]) => {
        this.dataSource = new MatTableDataSource(data);
      });
      this.dataSource = new MatTableDataSource(this.data);
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}


