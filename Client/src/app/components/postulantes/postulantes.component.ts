import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';
import { ApiService } from '../../services/api.service';

export interface Postulantes {
  candidateApplicationId: number;
  applicationDate: string;
  applicationStatus: string;
  userEntity: number;
  jobOffer: number;
  user:{
    name: string;
    last_name: string;
  
  }
}

@Component({
  selector: 'app-postulantes',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, MatInputModule, MatFormFieldModule, MatTableModule,],
  templateUrl: './postulantes.component.html',
  styleUrl: './postulantes.component.css'
})
export default class PostulantesComponent {
  constructor(private api: ApiService,
    private route: ActivatedRoute
  ) { }

  displayedColumns: string[] = ['applicationDate', 'userEntity', 'applicationStatus', 'curriculum', 'proceso'];
  data:any;
  dataSource:any;
  jobOfferId: string = '';
  jobOfferName: string = '';

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const jobOfferId = params.get('jobOfferId');
      const jobOfferName = params.get('jobOfferName');

      if (jobOfferId && jobOfferName) {
        this.jobOfferId = jobOfferId;
        this.jobOfferName = jobOfferName;

        if (typeof localStorage !== 'undefined') { // Esto fue añadido por localstorage indefinidio en el navegador
          this.api.getAPI('job-offer/all/'+this.jobOfferId).subscribe((data: Postulantes[]) => {
            this.dataSource = new MatTableDataSource(data);
          });
          this.dataSource = new MatTableDataSource(this.data);
          console.log(this.dataSource)
        }
      } else{
        this.jobOfferName = "Todas"
        if (typeof localStorage !== 'undefined') {
          this.api.getAPI('candidateApplications').subscribe((data: Postulantes[]) => {
            this.dataSource = new MatTableDataSource(data);
          });
          this.dataSource = new MatTableDataSource(this.data);
        }
      }
    });

    
  }


  viewProfile(element: any): void{
    window.location.href = '/ver-perfil?cardId=' + element.userEntity; 
  }

  viewProcess(element: any): void{
    console.log(element);
  }

}
