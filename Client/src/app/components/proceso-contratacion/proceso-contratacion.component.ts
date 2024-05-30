import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-proceso-contratacion',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, CommonModule],
  templateUrl: './proceso-contratacion.component.html',
  styleUrls: ['./proceso-contratacion.component.css']
})
export default class ProcesoContratacionComponent {
  step: number = 1;
  steps = Array.from({ length: 9 }, (_, i) => i + 1);
  stepTitles = [
    'Primera entrevista',
    'Pruebas psicotécnicas',
    'Verificaciones laborales',
    'Tipo de ingreso',
    'Documentación completa',
    'Exámenes médicos',
    'Contrato laboral',
    'Afiliación seguridad',
    'Proceso finalizado'
  ];
  
  estados: Record<number, boolean> = {
    2: false,
    3: false,
    4: false,
    5: false,
    6: false,
    7: false,
    8: false,
    9: false,
    10: false,
    11: false,
    12: false,
    13: false,
    14: false,
    15: false
  };

  nextStep() {
    if (this.step < this.steps.length) {
      this.step++;
    }
  }

  onSubmit(event: Event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario
    this.nextStep();
  }

  cancelProcess() {
    // Lógica para manejar la cancelación del proceso
    console.log('Proceso cancelado');
    this.step = 1; // Reiniciar al primer paso o realizar alguna otra acción
  }

  reloadPage(event: Event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
    window.location.reload(); // Recargar la página actual
  }

  toggleEstado(step: number) {
    this.estados[step] = !this.estados[step];
  }
}
