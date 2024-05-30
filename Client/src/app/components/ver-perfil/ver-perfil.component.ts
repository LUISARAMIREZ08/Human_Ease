import { Component, OnInit } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';
import HeaderComponent from '../header/header.component';
import BarraHerramientasComponent from '../barra-herramientas/barra-herramientas.component';


@Component({
  selector: 'app-ver-perfil',
  standalone: true,
  imports: [MatGridListModule, CommonModule, HeaderComponent, BarraHerramientasComponent],
  templateUrl: './ver-perfil.component.html',
  styleUrl: './ver-perfil.component.css'
})
export default class VerPerfilComponent{

  documentsStatus = {
    // true = documento subido, false = documento pendiente 
    hojaDeVida: true, //Toca que sea false por defecto, pero por ahora lo dejo en true para probar
    fotocopiaCedula: true,
    certificadosFormacion: true,
    certificadoEPS: true,
    libretaMilitar: false,
    certificadoEducacion: true,
    cartasExperienciaLaboral: true,
    docuemtosBeneficiarios: false,
  }
}
