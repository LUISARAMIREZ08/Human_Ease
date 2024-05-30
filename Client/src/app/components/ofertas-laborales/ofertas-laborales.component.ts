import { Component, OnInit} from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';
import SelectorOfertaComponent from '../selector-oferta/selector-oferta.component';
import ContenidoOfertaComponent from '../contenido-oferta/contenido-oferta.component';
import HeaderOfertasComponent from '../header-ofertas/header-ofertas.component';
import HeaderUsuarioLogueadoComponent from '../header-usuario-logueado/header-usuario-logueado.component';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-ofertas-laborales',
  standalone: true,
  imports: [CommonModule, MatSidenavModule, HeaderOfertasComponent, HeaderUsuarioLogueadoComponent, SelectorOfertaComponent, ContenidoOfertaComponent],
  templateUrl: './ofertas-laborales.component.html',
  styleUrls: ['./ofertas-laborales.component.css']
})
export default class OfertasLaboralesComponent implements OnInit{
  userRole: string = '';
  constructor(
    private loginService: LoginService
  ) {}

  ngOnInit() {
    this.userRole = this.loginService.getUserRole();
  }
  
  selectedOffer: string = 'oferta-1';

  selectOffer(offerId: string) {
    this.selectedOffer = offerId;
  }
}
