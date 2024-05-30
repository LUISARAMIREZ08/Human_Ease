import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';
import { ApiService } from '../../services/api.service';

export interface Postulacion {
  jobOfferId: string;
  jobOfferDate: string;
  jobOfferExpirationDate: string;
  jobOfferName: string;
  jobOfferDescription: string;
}

@Component({
  selector: 'app-postulaciones',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule],
  templateUrl: './postulaciones.component.html',
  styleUrl: './postulaciones.component.css'
})
export default class PostulacionesComponent {
  constructor(private api: ApiService,
    private router: Router, 
  ) { }

  displayedColumns: string[] = ['jobOfferDate', 'jobOfferExpirationDate', 'jobOfferName', 'jobOfferDescription', 'accion'];
  data:any;
  dataSource :any;

  ngOnInit(): void {
    if (typeof localStorage !== 'undefined') { // Esto fue añadido por localstorage indefinidio en el navegador
      this.api.getAPI('job-offer').subscribe((data: Postulacion[]) => {
        this.dataSource = new MatTableDataSource(data);
      });
      this.dataSource = new MatTableDataSource(this.data);
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  editPostulacion(element: any): void{
    console.log(element);
  }

  deletePostulacion(element: any): void{
    console.log(element);
  }

  viewPostulantes(element: any): void{
    window.location.href = '/postulantes?jobOfferId=' + element.jobOfferId + '&jobOfferName=' + element.jobOfferName;
  }
}
