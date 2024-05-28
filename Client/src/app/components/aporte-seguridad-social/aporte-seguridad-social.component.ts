import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-aporte-seguridad-social',
  standalone: true,
  imports: [CommonModule, HeaderComponent, BarraHerramientasComponent],
  templateUrl: './aporte-seguridad-social.component.html',
  styleUrl: './aporte-seguridad-social.component.css'
})
export default class AporteSeguridadSocialComponent {
  step: number = 1;
  nextStep() {
    const periodoInput = (document.getElementById('periodo') as HTMLInputElement).value;
    if (this.step === 1 && periodoInput) {
      this.step++;
    } else if (this.step !== 1) {
      this.step++;
    }
  }

  onSubmit(event: Event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario
    this.nextStep();
  }

  reloadPage(event: Event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
    window.location.reload(); // Recargar la página actual
  }
}
