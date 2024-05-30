import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';

export interface departamentos{
  departmentId: number;
  departmentName: string;
}


@Component({
  selector: 'app-crear-cargo',
  standalone: true,
  imports: [CommonModule, HeaderComponent, BarraHerramientasComponent, ReactiveFormsModule ],
  templateUrl: './crear-cargo.component.html',
  styleUrl: './crear-cargo.component.css'
})
export default class CrearCargoComponent{

  positionForm!: FormGroup;
  interfazDeshabilitada: boolean = false;

  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private snack: MatSnackBar,
  ) { }

  departamentos: departamentos[] = [];
  tiempo: string[] = ['Tiempo completo', 'Medio tiempo']
  requerimientos: string[] = ["Tener conocimiento en JavaScript", "Tener conocimiento en Angular"];
  requerimientosAgregados: string[] = [];


  ngOnInit(): void {
    // Get departamentos
    if (typeof localStorage !== 'undefined') {
      this.api.getAPI('departments').subscribe((data: departamentos[]) => {
        this.departamentos = data;
      });
    }

    // Formulario de creación de cargo
    this.positionForm = this.formBuilder.group({
      namePosition: new FormControl('', [Validators.required]),
      departmentId: new FormControl('', [Validators.required]),
      levelOfExperience: new FormControl('', [Validators.required]),
      employmentTime: new FormControl('', [Validators.required]),
    });
  }

  onSubmit() {
    console.log('Formulario de creación de cargo: ', this.positionForm.value);
    console.log('Formulario de creación de cargo válido: ', this.positionForm.valid);
    console.log('Errores del formulario: ', this.positionForm.errors);
    console.log('Errores en los campos del formulario: ', this.positionForm.controls);

    if (this.positionForm.valid) {
      const formData = this.positionForm.value;

      this.api.postAPI('position', formData).subscribe(
        (response: any) => {
          console.log('Cargo creado con éxito', response);
          const positionId = response.positionId;

          // Guardar los requerimientos
          this.requerimientosAgregados.forEach(requerimiento => {
            const requirementData = {
              descriptionRequirement: requerimiento,
              position: positionId
            };
            this.api.postAPI('requierements', requirementData).subscribe(
              (reqResponse: any) => {
                console.log('Requerimiento creado con éxito', reqResponse);
              },
              (reqError: any) => {
                console.error('Error al crear el requerimiento', reqError);
              }
            );
          });

          const snackBarRef = this.snack.open('Cargo creado con éxito', 'Aceptar', {
            duration: 6000
          });

          snackBarRef.onAction().subscribe(() => {
            window.location.reload();
          });

          snackBarRef.afterDismissed().subscribe(() => {
            window.location.reload(); // Recargar la página después de que pase el tiempo del snack
          });

          snackBarRef.afterDismissed().subscribe(() => {
            // Habilitar la interfaz de usuario después de que se cierre el snackbar
            this.habilitarInterfazUsuario();
          });

          // Deshabilitar la interfaz de usuario mientras el snackbar está visible
          this.deshabilitarInterfazUsuario();
        },
        (error: any) => {
          console.error('Error al crear el cargo', error);
          this.snack.open('Error al crear el cargo', 'Aceptar', {
            duration: 3000
          });
        }
      );
    }

  }

  deshabilitarInterfazUsuario() {
    this.interfazDeshabilitada = true;
  }

  habilitarInterfazUsuario() {
    this.interfazDeshabilitada = false;
  }

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
  }

  eliminarRequerimiento(index: number) {
    this.requerimientosAgregados.splice(index, 1);
  }
}
