import { Component, OnInit } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';
import HeaderUsuarioLogueadoComponent from '../header-usuario-logueado/header-usuario-logueado.component';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-usuario-cuenta',
  standalone: true,
  imports: [MatGridListModule, CommonModule, HeaderUsuarioLogueadoComponent],
  templateUrl: './usuario-cuenta.component.html',
  styleUrl: './usuario-cuenta.component.css'
})
export default class UsuarioCuentaComponent implements OnInit{

  userInfo:any;

  constructor(
    private loginService: LoginService
  ) {}

  ngOnInit() {
    this.userInfo = this.loginService.getUser();
  }

  documentsStatus = {
    // true = documento subido, false = documento pendiente 
    hojaDeVida: true, //Toca que sea false por defecto, pero por ahora lo dejo en true para probar
    fotocopiaCedula: false,
    certificadosFormacion: false,
    certificadoEPS: false,
    libretaMilitar: false,
    certificadoEducacion: false,
    cartasExperienciaLaboral: false,
    docuemtosBeneficiarios: false,
  }

}
