import { Component } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';
import { tap } from 'rxjs/operators';
import SelectorOfertaComponent from '../selector-oferta/selector-oferta.component';
import ContenidoOfertaComponent from '../contenido-oferta/contenido-oferta.component';
import HeaderOfertasComponent from '../header-ofertas/header-ofertas.component';
import HeaderUsuarioLogueadoComponent from '../header-usuario-logueado/header-usuario-logueado.component';
import HeaderComponent from '../header/header.component';
import { LoginService } from '../../services/login.service';
import { ApiService } from '../../services/api.service';

export interface Postulacion {
  jobOfferId: string;
  jobOfferDate?: string;
  jobOfferExpirationDate?: string;
  jobOfferName: string;
  jobOfferDescription?: string;
  positionId: number;
  postulacion?: {
    positionId: number;
    namePosition: string;
    departmentId: number;
    employmentTime: string;
    levelOfExperience: string;
    department: {
      departmentId: number;
      departmentName: string;
    };
  };
}

@Component({
  selector: 'app-ofertas-laborales',
  standalone: true,
  imports: [
    CommonModule, 
    MatSidenavModule,
    HeaderComponent,
    HeaderOfertasComponent, 
    HeaderUsuarioLogueadoComponent, 
    SelectorOfertaComponent, 
    ContenidoOfertaComponent
  ],
  templateUrl: './ofertas-laborales.component.html',
  styleUrls: ['./ofertas-laborales.component.css']
})
export default class OfertasLaboralesComponent {
  userRole: string = '';
  dataSource: Postulacion[] = [];
  selectedOffer: string = '';

  constructor(
    private api: ApiService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.userRole = this.loginService.getUserRole();

    if (typeof localStorage !== 'undefined') { // Esto fue añadido por localstorage indefinidio en el navegador
      this.api.getAPI('job-offer').subscribe((data: Postulacion[]) => {
        this.dataSource = data;

        // Por cada oferta laboral se busca la posición asociada y se agrega al objeto
        const requests = this.dataSource.map(element =>
          this.api.getAPI('position/' + element.positionId).pipe(
            tap(data => element.postulacion = data)
          )
        );

        forkJoin(requests).subscribe(() => {
          this.selectOffer(this.dataSource[0]?.jobOfferId);
        });
      });
    }
  }

  selectOffer(offerId: string) {
    this.selectedOffer = offerId;
  }
}
