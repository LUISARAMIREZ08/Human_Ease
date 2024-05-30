import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { ApiService } from '../../services/api.service';
import { LoginService } from '../../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

interface Aplication{
  applicationDate: string;
  applicationStatus: 'PENDING';
  jobOffer: number;
  userEntity: number;
}

@Component({
  selector: 'app-contenido-oferta',
  standalone: true,
  imports: [CommonModule, MatGridListModule],
  templateUrl: './contenido-oferta.component.html',
  styleUrls: ['./contenido-oferta.component.css']
})
export default class ContenidoOfertaComponent {

  constructor(
    private api: ApiService,
    private loginService: LoginService,
    private snack: MatSnackBar
  ) { }

  @Input() imgSrc: string = '';
  @Input() titulo: string = 'Titulo de oferta';
  @Input() cargo: string = 'Cargo';
  @Input() departamento: string = 'Departamento';
  @Input() nivelExperiencia: string = 'Nivel de experiencia';
  @Input() tiempoEmpleo: string = 'Tiempo de empleo';
  @Input() fechaCreacion: string = 'Fecha de creación';
  @Input() fechaCierre: string = 'Fecha de cierre';
  @Input() descripcion: string = 'Descripción de la oferta';
  @Input() offerId: string = '';
  @Input() selectedOffer: string = '';

  userInfo: any;
  aplication: Aplication = {
    applicationDate: '',
    applicationStatus: 'PENDING',
    jobOffer: 0,
    userEntity: 0
  };

  ngOnInit() {
    this.userInfo = this.loginService.getUser();
  }

  aplicationOffer(): void {
    this.aplication.jobOffer = parseInt(this.offerId);
    if (this.userInfo === null) {
      return;
    }
    this.aplication.userEntity = parseInt(this.userInfo.cardId);
    this.aplication.applicationDate = new Date().toISOString().split('T')[0];
    console.log(this.aplication);
    this.api.postAPI('candidateApplications', this.aplication).subscribe((data: any) => {
      window.location.href = '/procesos-activos'
    },
    (error: any) => {
      console.log(error);
      this.snack.open("Ya te postulaste a esta oferta",'Cerrar'),{
        duration:3000
      }
  });
}
}