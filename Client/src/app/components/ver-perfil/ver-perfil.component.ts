import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';
import { ApiService } from '../../services/api.service';

export interface usuario{
  cardId: number;
  name: string;
  lastName: string;
  username: string;
  birthDate: string;
  address: string;
  phone: string;
  email: string;


}

@Component({
  selector: 'app-ver-perfil',
  standalone: true,
  imports: [MatGridListModule, CommonModule, HeaderComponent, BarraHerramientasComponent],
  templateUrl: './ver-perfil.component.html',
  styleUrl: './ver-perfil.component.css'
})
export default class VerPerfilComponent{
  constructor(
    private api: ApiService,
    private route: ActivatedRoute
  ) { }

  data:any;
  dataSource:any;

  documentsStatus = {
    // true = documento subido, false = documento pendiente 
    hojaDeVida: true, //Toca que sea false por defecto, pero por ahora lo dejo en true para probar
    fotocopiaCedula: true,
    certificadosFormacion: true,
    certificadoEPS: true,
    libretaMilitar: false,
    certificadoEducacion: true,
    cartasExperienciaLaboral: true,
    docuemtosBeneficiarios: false,
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const CardId = params.get('cardId');

      if (CardId) {
        if (typeof localStorage !== 'undefined') { // Esto fue añadido por localstorage indefinidio en el navegador
          this.api.getAPI('user/' + CardId).subscribe((data: usuario) => {
            this.dataSource = data;
          });
        }
      };
    });
  }
}
