import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';
import { ApiService } from '../../services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

export interface usuario{
  cardId: number;
  name: string;
  lastName: string;
  username: string;
  birthDate: string;
  address: string;
  phone: string;
  email: string;


}

@Component({
  selector: 'app-ver-perfil',
  standalone: true,
  imports: [MatGridListModule, CommonModule, HeaderComponent, BarraHerramientasComponent],
  templateUrl: './ver-perfil.component.html',
  styleUrl: './ver-perfil.component.css'
})
export default class VerPerfilComponent{
  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private snack: MatSnackBar
  ) { }

  data:any;
  dataSource:any;
  documents:any;

  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(){
    this.route.queryParamMap.subscribe(params => {
      const CardId = params.get('cardId');

      if (CardId) {
        if (typeof localStorage !== 'undefined') { // Esto fue añadido por localstorage indefinidio en el navegador
          this.api.getAPI('user/' + CardId).subscribe((data: usuario) => {
            this.dataSource = data;
            this.getDocuments(); 
          });
        }
      };
    });
  }

  getDocuments(){
    if(typeof localStorage !== 'undefined'){
      this.api.getAPI("documents/person/"+this.dataSource.cardId).subscribe((data: any) => {
        this.documents = data;
        this.verifyDocumentsStatus()
      });
    }
  }

  visualizer(documentName: string){
    const existingDocument = this.documents.find((document: any) => document.documentName === documentName);
    if(existingDocument){
      window.open(existingDocument.documentPath, '_blank');
    }
    else {
      this.snack.open("No se ha encontrado documento.", "Aceptar"),{
        duration:3000
      }
    }
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
