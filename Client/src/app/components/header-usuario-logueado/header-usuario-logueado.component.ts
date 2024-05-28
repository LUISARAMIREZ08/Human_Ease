import { Component } from '@angular/core';
import {MatMenuModule} from '@angular/material/menu';

@Component({
  selector: 'app-header-usuario-logueado',
  standalone: true,
  imports: [MatMenuModule],
  templateUrl: './header-usuario-logueado.component.html',
  styleUrl: './header-usuario-logueado.component.css'
})
export default class HeaderUsuarioLogueadoComponent {

}
