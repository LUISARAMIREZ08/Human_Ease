import { Component } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';
import SelectorOfertaComponent from '../selector-oferta/selector-oferta.component';
import ContenidoOfertaComponent from '../contenido-oferta/contenido-oferta.component';
import HeaderOfertasComponent from '../header-ofertas/header-ofertas.component';

@Component({
  selector: 'app-ofertas-laborales',
  standalone: true,
  imports: [CommonModule, HeaderOfertasComponent, MatSidenavModule, SelectorOfertaComponent, ContenidoOfertaComponent],
  templateUrl: './ofertas-laborales.component.html',
  styleUrls: ['./ofertas-laborales.component.css']
})
export default class OfertasLaboralesComponent {
  selectedOffer: string = 'oferta-1';

  selectOffer(offerId: string) {
    this.selectedOffer = offerId;
  }
}
