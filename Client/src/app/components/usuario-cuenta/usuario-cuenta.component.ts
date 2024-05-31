import { Component, OnInit } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';
import HeaderUsuarioLogueadoComponent from '../header-usuario-logueado/header-usuario-logueado.component';
import { LoginService } from '../../services/login.service';
import { FileUploadService } from '../../services/file-upload.service';
import { ApiService } from '../../services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-usuario-cuenta',
  standalone: true,
  imports: [MatGridListModule, CommonModule, HeaderUsuarioLogueadoComponent],
  templateUrl: './usuario-cuenta.component.html',
  styleUrl: './usuario-cuenta.component.css'
})
export default class UsuarioCuentaComponent implements OnInit{

  userInfo:any;
  documents: any;

  constructor(
    private loginService: LoginService,
    private fileUpload: FileUploadService,
    private api: ApiService,
    private snack:MatSnackBar
  ) {}

  ngOnInit() {
    this.userInfo = this.loginService.getUser();
    if(typeof localStorage !== 'undefined'){
      this.api.getAPI("documents/person/"+this.userInfo.cardId).subscribe((data: any) => {
        this.documents = data;
        this.verifyDocumentsStatus()
        console.log('Documentos: ', this.documents);
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
              cardId: this.userInfo.cardId
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

  // Función para verificar estados a partir de los documentos entregados del endpoint
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

}
