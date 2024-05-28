import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

@Component({
  selector: 'app-crear-cargo',
  standalone: true,
  imports: [CommonModule, HeaderComponent, BarraHerramientasComponent],
  templateUrl: './crear-cargo.component.html',
  styleUrl: './crear-cargo.component.css'
})
export default class CrearCargoComponent {
  departamentos: string[] = ['Desarrollo', 'Diseño', 'Marketing'];
  experiencia: string[] = ['Junior', 'Mid', 'Senior'];
  tiempo: string[] = ['Tiempo completo', 'Medio tiempo']
  requerimientos: string[] = ["Tener conocimiento en JavaScript", "Tener conocimiento en Angular"];
  requerimientosAgregados: string[] = [];

  agregarRequerimiento(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const requerimiento = selectElement.value;
    if (requerimiento && !this.requerimientosAgregados.includes(requerimiento)) {
      this.requerimientosAgregados.push(requerimiento);
      selectElement.value = ''; // reset select
    }
  }

  agregarRequerimientoPersonalizado() {
    const inputElement = document.getElementById('requerimiento-personalizado') as HTMLInputElement;
    const requerimientoPersonalizado = inputElement.value;
    if (requerimientoPersonalizado && !this.requerimientosAgregados.includes(requerimientoPersonalizado)) {
      this.requerimientosAgregados.push(requerimientoPersonalizado);
      inputElement.value = ''; // reset input
    }
    console.log(this.requerimientosAgregados);
  }

  eliminarRequerimiento(index: number) {
    this.requerimientosAgregados.splice(index, 1);
  }

  onSubmit(form: any) {
    const formData = {
      ...form.value,
      requerimientos: this.requerimientosAgregados
    };
    console.log(formData);
  }
}
