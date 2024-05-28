import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-selector-oferta',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './selector-oferta.component.html',
  styleUrls: ['./selector-oferta.component.css']
})
export default class SelectorOfertaComponent {
  @Input() imgSrc: string = '';
  @Input() titulo: string = 'Titulo de oferta';
  @Input() cargo: string = 'Cargo de oferta';
  @Input() departamento: string = 'Departamento de oferta';
  @Input() offerId: string = '';
  @Input() selectedOffer: string = '';

  @Output() selectOffer: EventEmitter<string> = new EventEmitter<string>();

  handleClick() {
    this.selectOffer.emit(this.offerId);
  }
}
