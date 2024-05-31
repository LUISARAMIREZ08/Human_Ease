import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiService } from '../../services/api.service';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';


export interface position{
  positionId: number;
  namePosition: string;
  departmentId: number;
  employmentTime: string;
  salary: number;
  levelOfExperience: string;
  department: {
    departmentId: number;
    departmentName: string;
  };
}

export interface employee{
  employeeId: number;
  CardId: string;
  namePosition: string;
  employeeName: number;
  employeeLastName: string;
}

@Component({
  selector: 'app-crear-postulacion',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule, HeaderComponent, BarraHerramientasComponent],
  templateUrl: './crear-postulacion.component.html',
  styleUrl: './crear-postulacion.component.css'
})
export default class CrearPostulacionComponent {
  jobOfferForm!: FormGroup;
  interfazDeshabilitada: boolean = false;
  
  position: position[] = [];
  employee: employee[] = [];
  positionId: number = 0;
  selectedDepartment: string = '';
  selectedExperience: string = '';
  selectedTime: string = '';

  constructor (
    private api: ApiService,
    private formBuilder: FormBuilder, 
    private snack: MatSnackBar,
  ){}

  ngOnInit(): void {
    // Get positions
    if (typeof localStorage !== 'undefined') {
      this.api.getAPI('position').subscribe((data: position[]) => {
        this.position = data;
      });
    }

    // Get employess ADMIN
    if (typeof localStorage !== 'undefined') {
      this.api.getAPI('employee/admins').subscribe((data: employee[]) => {
        this.employee = data;
      });
    }


    // Formulario de creación de postulación
    this.jobOfferForm = this.formBuilder.group({
      jobOfferName: new FormControl('', [Validators.required]),
      jobOfferDescription: new FormControl('', [Validators.required]),
      jobOfferDate: new FormControl('', [Validators.required]),
      jobOfferExpirationDate: new FormControl('', [Validators.required]),
      salary: new FormControl('', [Validators.required]),
      jobOfferStatus: 'ACTIVE',
      positionId: new FormControl('', [Validators.required]),
      employeeId: new FormControl('', [Validators.required])
    });


  };

  changeSelect(): void {
    this.positionId=this.jobOfferForm.value.positionId;

    this.position.forEach((element) => {
      if (element.positionId == this.positionId) {
        console.log(element);
        this.selectedDepartment = element.department.departmentName;
        this.selectedExperience = element.levelOfExperience;
        this.selectedTime = element.employmentTime;
      }
    });
  }

  onSubmit(): void {
    console.log('Formulario de creación de postulación: ', this.jobOfferForm.value);
    console.log('Formulario de creación de postulación válido: ', this.jobOfferForm.valid);
    console.log('Errores del formulario: ', this.jobOfferForm.errors);
    console.log('Errores en los campos del formulario: ', this.jobOfferForm.controls);

    if (this.jobOfferForm.valid) {
      const formData = this.jobOfferForm.value;

      this.api.postAPI('job-offer', formData).subscribe(
        (response: any) => {
          console.log('Postulación creada con éxito', response);
          this.snack.open('Postulación creada con éxito', 'Aceptar', {
            duration: 3000
          });

          const snackBarRef = this.snack.open('Cargo creado con éxito', 'Aceptar', {
            duration: 6000
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
          console.error('Error al crear la postulación', error);
          this.snack.open('Error al crear la postulación', 'Aceptar', {
            duration: 3000
          });
        }
      );

    } else {
      this.snack.open('Por favor, complete el formulario correctamente', 'Aceptar', {
        duration: 3000
      });
    }
  }

  deshabilitarInterfazUsuario() {
    this.interfazDeshabilitada = true;
  }

  habilitarInterfazUsuario() {
    this.interfazDeshabilitada = false;
  }

}
