import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface Postulacion {
  fecha_apertura: string;
  fecha_cierre: string;
  titulo_oferta: string;
  descripcion: string;
}

const ELEMENT_DATA: Postulacion[] = [
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cocinero', descripcion: 'Se necesita cocinero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Mesero', descripcion: 'Se necesita mesero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cajero', descripcion: 'Se necesita cajero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cocinero', descripcion: 'Se necesita cocinero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Mesero', descripcion: 'Se necesita mesero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cajero', descripcion: 'Se necesita cajero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cocinero', descripcion: 'Se necesita cocinero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Mesero', descripcion: 'Se necesita mesero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cajero', descripcion: 'Se necesita cajero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Cocinero', descripcion: 'Se necesita cocinero con experiencia'},
  {fecha_apertura: '2021-10-01', fecha_cierre: '2021-10-10', titulo_oferta: 'Mesero', descripcion: 'Se necesita mesero con experiencia'}
];

@Component({
  selector: 'app-postulaciones',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule],
  templateUrl: './postulaciones.component.html',
  styleUrl: './postulaciones.component.css'
})
export default class PostulacionesComponent {
  displayedColumns: string[] = ['fecha_apertura', 'fecha_cierre', 'titulo_oferta', 'descripcion'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
