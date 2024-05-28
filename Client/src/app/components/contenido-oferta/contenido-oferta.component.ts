import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';

@Component({
  selector: 'app-contenido-oferta',
  standalone: true,
  imports: [CommonModule, MatGridListModule],
  templateUrl: './contenido-oferta.component.html',
  styleUrls: ['./contenido-oferta.component.css']
})
export default class ContenidoOfertaComponent {
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
}
