import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';
import { ApiService } from '../../services/api.service';
import { FileUploadService } from '../../services/file-upload.service';

@Component({
  selector: 'app-proceso-contratacion',
  standalone: true,
  imports: [HeaderComponent, BarraHerramientasComponent, CommonModule,ReactiveFormsModule],
  templateUrl: './proceso-contratacion.component.html',
  styleUrls: ['./proceso-contratacion.component.css']
})

export default class ProcesoContratacionComponent {
  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder, 
    private snack: MatSnackBar,
    private fileUpload: FileUploadService,
  ) { }

  InterviewInfoForm!: FormGroup;
  InterviewResultsForm!: FormGroup;

  viewInterviewInfoForm: boolean = false;
  viewInterviewResultsForm: boolean = false;

  documents: any;

  documentsStatus = {
    // true = documento subido, false = documento pendiente 
    hojaDeVida: false, //Toca que sea false por defecto, pero por ahora lo dejo en true para probar
    fotocopiaCedula: false,
    certificadosFormacion: false,
    certificadoEPS: false,
    libretaMilitar: false,
    certificadoEducacion: false,
    cartasExperienciaLaboral: false,
    documentosBeneficiarios: false,
  }

  candidateApplicationId: any;
  applicationStatus: any;
  applicationDate: any;
  jobOfferName: any;
  jobOfferId: any;
  jobOfferDescription: any;
  userEntity: any;
  name: any;
  lastName: any;
  phone: any;
  email: any;

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
    2: false, //Agendar entrevista
    3: false, //Ingresar informacion de entrevista
    4: false, //Ingresar resultados de la entrevista
    5: false, //Verificación de pruebas psicotécnicas
    6: false, //Verificación de referencias laborales
    7: false, //Verticar antecedes laborales
    8: false, //Definir el tipo de ingreso
    9: false, //Verificar documentación
    10: false, //Enviar solicitud de exámenes médicos
    11: false, //Subir resultados de exámenes médicos
    12: false, //Realizar contrato
    13: false, //Afiliación a ARL Colpatria
    14: false, //Afiliacion a CDC Confamiliar
    15: false, //Afiliacion SOS SURA
    16: false, //Afiliacion a EPS Coomeva
    17: false, //Finalizacion proceso
  };

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
       this.candidateApplicationId = params.get('candidateApplicationId');
       this.applicationDate = params.get('applicationDate');
       this.jobOfferId = params.get('jobOfferId');
       this.userEntity = params.get('userEntity');
       this.name = params.get('name');
       this.lastName = params.get('lastName');
       this.phone = params.get('phone');
       this.email = params.get('email');
    });
    if (typeof localStorage !== 'undefined') {
      this.api.getAPI('candidateApplications/' + this.candidateApplicationId).subscribe((data: any) => {
        this.applicationStatus = data.applicationStatus;
        console.log(this.applicationStatus);

        if(this.applicationStatus == 'FIRST_INTERVIEW'){
          this.estados[2] = true;
          this.estados[3] = true;
        } else if (this.applicationStatus == 'PSYCHOTHENIC_TESTING'){
          this.step = 2;
        } else if (this.applicationStatus == 'VERIFICATIONS'){
          this.step = 3;
        } else if (this.applicationStatus == 'TYPE_ENTRY'){
          this.step = 4;
        } else if (this.applicationStatus == 'DOCUMENTATION'){
          this.step = 5;
          this.verifyDocumentsStatus();
          //Si estan todos los documentos se habilita el siguiente paso
          if (this.documentsStatus.hojaDeVida && this.documentsStatus.fotocopiaCedula && this.documentsStatus.certificadosFormacion && this.documentsStatus.certificadoEPS && this.documentsStatus.libretaMilitar && this.documentsStatus.certificadoEducacion && this.documentsStatus.cartasExperienciaLaboral && this.documentsStatus.documentosBeneficiarios){
            this.estados[9] = true;
          }
        } else if (this.applicationStatus == 'MEDICAL_EXAM'){
          this.step = 6;
        } else if (this.applicationStatus == 'EMPLOYMENT_CONTRACT'){
          this.step = 7;
        } else if (this.applicationStatus == 'SECURITY_AFFILIATION'){
          this.step = 8;
        } else if (this.applicationStatus == 'SELECTED'){
          this.step = 9;
        }
      });
      
    }
    

    this.InterviewInfoForm = this.formBuilder.group({
      interviewDate: new FormControl('',[Validators.required]),
      interviewTime: new FormControl('',[Validators.required]),
      result: "Esperando realizacion de la entrevista",
      link: new FormControl('',[Validators.required]),
      interviewStatus: "SCHEDULED",
      interviewType: new FormControl('',[Validators.required]),
      candidateApplicationId: this.candidateApplicationId
    })

    this.InterviewResultsForm = this.formBuilder.group({
      result: new FormControl('',[Validators.required]),
      interviewStatus: new FormControl('',[Validators.required]),
      candidateApplicationId: this.candidateApplicationId
    })

    //Obtener el nombre de la postulación
    if (typeof localStorage !== 'undefined') {
      this.api.getAPI('job-offer/' + this.jobOfferId).subscribe((data: any) => {
        this.jobOfferName = data.jobOfferName;
        this.jobOfferDescription = data.jobOfferDescription;
      });
    }

    //Obtener los documentos de la postulación
    if(typeof localStorage !== 'undefined'){
      this.api.getAPI("documents/person/"+this.userEntity).subscribe((data: any) => {
        this.documents = data;
        this.documents.forEach((document: any) => {
          if (document.documentName == 'medicalExam'){
            this.estados[10] = true;
            this.estados[11] = true;
          }
        });
      });
    }
  }

  uploadFile(event: Event, documentName: string){
    this.snack.open("El documento se está subiendo."),{
      duration:3000
    }
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];

      const existingDocument = this.documents.find((document: any) => document.documentName === documentName);

      if(existingDocument){
        this.fileUpload.uploadFile(file, documentName).subscribe(url => {
          try {
            const document = {
              documentName: documentName,
              documentPath: url
            };
            this.api.putAPI("documents/"+existingDocument.documentId, document).subscribe((data: any) => {
              this.delay(2000);
              this.snack.open("Se ha actualizado el documento.", "Aceptar"),{
                duration:3000
              }
            });
          } catch (Error) {
            this.snack.open("Error al actualizar el documento, vuelva a intentar.", "Aceptar"),{
              duration:3000
            }
          }
        });
      } else {
        this.fileUpload.uploadFile(file, documentName).subscribe(url => {
          try {
            const document = {
              documentName: documentName,
              documentPath: url,
              cardId: this.userEntity
            };
            this.api.postAPI("documents", document).subscribe((data: any) => {
              this.delay(2000);
              window.location.reload();
            });
          } catch (Error) {
            console.error('Error al subir el archivo: ', Error);
          }
        });
      }
    }
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(resolve, ms));
  }

  verifyDocumentsStatus(){
    this.documents.forEach((document: any) => {
      if (document.documentName === 'hojaDeVida') {
        this.documentsStatus.hojaDeVida = true;
      } else if (document.documentName === 'fotocopiaCedula') {
        this.documentsStatus.fotocopiaCedula = true;
      } else if (document.documentName === 'certificadosFormacion') {
        this.documentsStatus.certificadosFormacion = true;
      } else if (document.documentName === 'certificadoEPS') {
        this.documentsStatus.certificadoEPS = true;
      } else if (document.documentName === 'libretaMilitar') {
        this.documentsStatus.libretaMilitar = true;
      } else if (document.documentName === 'certificadoEducacion') {
        this.documentsStatus.certificadoEducacion = true;
      } else if (document.documentName === 'cartasExperienciaLaboral') {
        this.documentsStatus.cartasExperienciaLaboral = true;
      } else if (document.documentName === 'documentosBeneficiarios') {
        this.documentsStatus.documentosBeneficiarios = true;
      }
    });
  }

  changeStateApplication(state: string){
    const formUpdateStatus = {
      candidateApplicationId: this.candidateApplicationId,
      applicationStatus: state
    }

    this.api.putAPI('candidateApplications/updateStatus', formUpdateStatus).subscribe(
      (response: any) => {
        console.log('Estado de la postulación actualizado',response);
        this.applicationStatus = state;
      },
      (error: any) => {
        console.error('Error al actualizar el estado de la postulación', error);
        this.snack.open('Error al actualizar el estado de la postulación', 'Aceptar', {
          duration: 3000
        });
      }
    );
  }

  nextStep() {
    console.log(this.step)
    if (this.step <= this.steps.length) {
      // Como ya pasamos al siguiente paso, se cambia el estado de la aplicacion del usuario a PSYCHOTHENIC_TESTING
      if (this.step == 1){
        this.changeStateApplication('PSYCHOTHENIC_TESTING')
      } else if (this.step == 2){
        this.changeStateApplication('VERIFICATIONS')
      } else if (this.step == 3){
        this.changeStateApplication('TYPE_ENTRY')
      } else if (this.step == 4){
        this.changeStateApplication('DOCUMENTATION')
        this.verifyDocumentsStatus();
        //Si estan todos los documentos se habilita el siguiente paso
        if (this.documentsStatus.hojaDeVida && this.documentsStatus.fotocopiaCedula && this.documentsStatus.certificadosFormacion && this.documentsStatus.certificadoEPS && this.documentsStatus.libretaMilitar && this.documentsStatus.certificadoEducacion && this.documentsStatus.cartasExperienciaLaboral && this.documentsStatus.documentosBeneficiarios){
          this.estados[9] = true;
        }
      } else if (this.step == 5){
        this.changeStateApplication('MEDICAL_EXAM')
      } else if (this.step == 6){
        this.changeStateApplication('EMPLOYMENT_CONTRACT')
      } else if (this.step == 7){
        this.changeStateApplication('SECURITY_AFFILIATION')
      } else if (this.step == 9){
        this.changeStateApplication('SELECTED')
      }

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
    //Cambiamos el estado de la postulación a REJECTED
    this.changeStateApplication('REJECTED')
  }

  reloadPage(event: Event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
    window.location.reload(); // Recargar la página actual
  }

  toggleEstado(step: number) {
    if (this.estados[step] != true){
      this.estados[step] = true;
    }
  }

  viewInterviewInfo(){
    this.viewInterviewInfoForm = !this.viewInterviewInfoForm;
  }

  viewInterviewResults(){
    this.viewInterviewResultsForm = !this.viewInterviewResultsForm;
  }

  onSubmitInterviewInfo(){
    console.log('Formulario de interview info:', this.InterviewInfoForm.value);
    console.log('Formulario de interview infor valido: ', this.InterviewInfoForm.valid);
    console.log('Errores del formulario: ', this.InterviewInfoForm.errors);
    console.log('Errores en los campos del formulario:', this.InterviewInfoForm.controls);

    if(this.InterviewInfoForm.valid){
      const formData = this.InterviewInfoForm.value;

      this.api.postAPI('interview', formData).subscribe(
        (response: any) => {
        console.log('Información de entrevista agregada',response);
        
        const snackBarRef = this.snack.open('Información de entrevista cargada con éxito', 'Aceptar', {
          duration: 6000
        });  
        
        //Cambiamos el estado a FIRST_INTERVIEW
        this.changeStateApplication('FIRST_INTERVIEW')
        this.toggleEstado(3);

        },
        (error: any) => {
          console.error('Error al ingresar la información de la entrevista', error);
          this.snack.open('Error al ingresar la información de la entrevista', 'Aceptar', {
            duration: 3000
          });
        }
      );
    }
  }

  onSubmitInterviewResults(){
    console.log('Formulario de interview results:', this.InterviewResultsForm.value);
    console.log('Formulario de interview results valido: ', this.InterviewResultsForm.valid);
    console.log('Errores del formulario: ', this.InterviewResultsForm.errors);
    console.log('Errores en los campos del formulario:', this.InterviewResultsForm.controls);

    if(this.InterviewResultsForm.valid){
      const formData = this.InterviewResultsForm.value;

      this.api.putAPI('interview/update', formData).subscribe(
        (response: any) => {
        console.log('Resultados de entrevista agregados',response);
        
        const snackBarRef = this.snack.open('Resultados de entrevista cargados con éxito', 'Aceptar', {
          duration: 6000
        });  
        this.toggleEstado(4);
        },
        (error: any) => {
          console.log(formData)
          console.error('Error al ingresar los resultados de la entrevista', error);
          this.snack.open('Error al ingresar los resultados de la entrevista', 'Aceptar', {
            duration: 3000
          });
        }
      );
    }
  }

  enviarEmail(email:string, data:any): boolean{
    this.api.postAPI('mail/send/'+email, data).subscribe(
      (response: any) => {
        console.log('Correo enviado',response);
        this.snack.open('Correo enviado con éxito', 'Aceptar', {
          duration: 3000
        });
        return true;
      },
      (error: any) => {
        console.error(error.typeof)
        console.error('Error al enviar el correo', error);
        this.snack.open('Error al enviar el correo', 'Aceptar', {
          duration: 3000
        });
        return false;
      }
    );
    return false;
  }

  envioEmpresaTemporal(email:string){

    //Solicitud de salario a la ofertaID
    this.api.getAPI('job-offer').subscribe((data: any) => {
      data.forEach((jobOffer: any) => {
        if (jobOffer.jobOfferId == this.jobOfferId){
          this.salary = jobOffer.salary;
        }
      });
      const infoEmail = {
        subject: 'Envio de información del candidato ' + this.name + ' ' + this.lastName,
        message: 'El candidato ' + this.name + ' ' + this.lastName + ' ha sido seleccionado para el cargo de ' + this.jobOfferName + 
        ' y se postuló el ' + this.applicationDate + 
        '\nSalario: '+ this.salary +
        '\nLa descripción de la oferta de trabajo fue la siguiente: \n' + this.jobOfferDescription +
        '\nLa información de contacto es la siguiente: \n' +
        'Teléfono: ' + this.phone + '\n' +
        'Correo: ' + this.email + '\n' +
        '\n Por favor, contactar al candidato para continuar con el proceso de contratación.',
      }
  
      const status= this.enviarEmail(email, infoEmail);
      this.changeStateApplication('REJECTED')
    });
  }
  
  solicitudExamenMedico(email:string){
    const infoEmail = {
      subject: 'Solicitud de exámenes médicos para el candidato ' + this.name + ' ' + this.lastName,
      message: 'El candidato ' + this.name + ' ' + this.lastName + ' ha sido seleccionado para el cargo de ' + this.jobOfferName + 
      ' y se postuló el ' + this.applicationDate + 
      '\nLa información de contacto es la siguiente: \n' +
      'Teléfono: ' + this.phone + '\n' +
      'Correo: ' + this.email + '\n' +
      '\n Por favor, contactar al candidato para coordinar los exámenes médicos. Recuerde enviar los resultados a la brevedad.',
    }

    this.enviarEmail(email, infoEmail);
    this.estados[10] = true;
  }

  enviarAfiliacionEPS(email:string){
    const infoEmail = {
      subject: 'Afiliación a EPS para el candidato ' + this.name + ' ' + this.lastName,
      message: 'Relaciono datos de nueva empleada para afiliación a Eps Coomeva:\n'+
      'Nombre: ' + this.name + ' ' + this.lastName + '\n' +
      'Cedula: ' + this.userEntity + '\n' +
      'Teléfono: ' + this.phone + '\n' +
      'Cargo: ' + this.jobOfferName + '\n' +
      'Salario: ' + this.salary + '\n' +
      '\n\n' +
      'Datos empresa: \n'+
      'Razón social: Localizamos TSA S.A.S.\n'+
      'Nit: 900.619.003-5\n'+
      'Dirección: Calle 1 No. 12 - 18 Barrio Popular Modelo\n'+
      'Teléfono: 3444050\n'+
      'Mil gracias por su colaboración y quedo atenta a sus comentarios. '
    }

    const status=this.enviarEmail(email, infoEmail);
    this.estados[16] = true;
  }

  enviarNotificacion(){
    const infoEmail = {
      subject: 'Notificación de selección para el cargo ' + this.jobOfferName,
      message: 'Felicitaciones ' + this.name + ' ' + this.lastName + ',\n'+
      'Has finalizado correctamente el proceso para el cargo de ' + this.jobOfferName + ' en Localizamos TSA S.A.S.\n'+
      '¡Bienvenido a esta gran familia!'
    }

    this.enviarEmail(this.email, infoEmail);
    this.estados[17] = true;
  }

  salary: any;
  realizarContrato(){

    this.api.getAPI('job-offer').subscribe((data: any) => {
      data.forEach((jobOffer: any) => {
        if (jobOffer.jobOfferId == this.jobOfferId){
          this.salary = jobOffer.salary;
        }
      });
      const employeeData = {
        employeeId: this.userEntity,
        CardId: this.userEntity,
        accountNumberEmployee : 123456789,
        salaryBase: this.salary,
        statusEmployee: 'ACTIVE',
        costCenterId: 1,
        positionId: this.jobOfferId,
      }
      console.log(employeeData);

      this.api.postAPI('employee', employeeData).subscribe(
        (response: any) => {
          console.log('Empleado creado con éxito', response);
          this.estados[12] = true;
        },
        (error: any) => {
          console.error('Error al crear el empleado', error);
        }
      );
    });
  }
}