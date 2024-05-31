import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import HeaderUsuarioLogueadoComponent from '../header-usuario-logueado/header-usuario-logueado.component';
import HeaderComponent from '../header/header.component';
import { ApiService } from '../../services/api.service';
import { LoginService } from '../../services/login.service';

export interface Aplication {
  candidateApplicationId: number;
  applicationDate: string;
  applicationStatus: string;
  jobOffer: number;
  userEntity: number;
  link: string;
  titulo_postulacion: string;
  cargo: string;
  fecha: string;
}

export interface Interview{
  link: string;
  candidateApplicationId: number;
}

interface Dictionary {
  [key: string]: string;
}

@Component({
  selector: 'app-procesos-activos',
  standalone: true,
  imports: [MatTableModule, CommonModule, HeaderUsuarioLogueadoComponent,HeaderComponent ],
  templateUrl: './procesos-activos.component.html',
  styleUrl: './procesos-activos.component.css'
})

export default class ProcesosActivosComponent {
  userRole: string = '';
  estadosDict: Dictionary = {
    'PENDING': 'Pendiente de revisión',
    'FIRST_INTERVIEW': 'Entrevista',
    'PSYCHOTHENIC_TESTING': 'Prueba psicotecnica',
    'VERIFICATIONS': 'Verificacion de experiencia laboral',
    'TYPE_ENTRY': 'Definiendo tipo de ingreso',
    'DOCUMENTATION': 'Esperando a documentación completa',
    'MEDICAL_EXAM': 'Examen medico ocupacional',
    'EMPLOYMENT_CONTRACT': 'Elaboración del Contrato de trabajo',
    'PRESELECTED': 'Preseleccionado',
    'REJECTED': 'Rechazado',
    'SELECTED': 'Seleccionado'
  }

  constructor(
    private api: ApiService,
    private loginService: LoginService
  ) { }
  displayedColumns: string[] = ['applicationDate', 'titulo_postulacion', 'cargo', 'applicationStatus', 'fecha'];
  dataSource: any;
  userInfo: any;

  ngOnInit() {
    this.userInfo = this.loginService.getUser();
    this.userRole = this.loginService.getUserRole();
    
    if (typeof localStorage !== 'undefined') {
      this.api.getAPI('candidateApplications').subscribe((data: Aplication[]) => {
        //Filtramos data para que solo quede las aplicaciones del usuario logueado
        data = data.filter((aplication) => aplication.userEntity == this.userInfo.cardId);

        data.forEach((aplication) => {
          //Se establece el link de la oferta segun el estado de la aplicacion
          if (aplication.applicationStatus == 'FIRST_INTERVIEW') {
            this.api.getAPI('interview').subscribe((data: Interview[]) => {
              data = data.filter((interview) => interview.candidateApplicationId == aplication.candidateApplicationId);
              aplication.link = data[0].link;
            });
          } else if (aplication.applicationStatus == 'PSYCHOTHENIC_TESTING') {
            aplication.link = 'https://docs.google.com/forms/d/e/1FAIpQLSdd3eZ4aomOqJFFjje2gH707RxEpLtWqyk_0rUGmwtLIhLuAw/viewform?usp=sf_link'; //Link al formulario de google
          } else if (aplication.applicationStatus == 'DOCUMENTATION') {
            aplication.link = '/usuario-cuenta';
          }

          //Se hace la peticion a la api para obtener la informacion de la oferta
          this.api.getAPI('job-offer/' + aplication.jobOffer).subscribe((data: any) => {
            aplication.titulo_postulacion = data.jobOfferName;
            aplication.fecha = data.jobOfferExpirationDate;

            //Se hace la peticion a la api para obtener la informacion del cargo
            this.api.getAPI('position/' + data.positionId).subscribe((data: any) => {
              aplication.cargo = data.namePosition;
            });
          });
        });
        this.dataSource = new MatTableDataSource(data);
      });
    }
  }

}
