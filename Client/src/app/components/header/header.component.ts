import { Component } from '@angular/core';
import {MatMenuModule} from '@angular/material/menu';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatMenuModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export default class HeaderComponent {
  constructor(
    private loginService: LoginService
  ) { }

  logout(){
    this.loginService.logout();
    window.location.href = '/login';
  }

}
