import { Component} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{

  username: string = '';
  password: string= '';
 
  constructor(
    private loginService: LoginService, 
    private router: Router, 
    private snack:MatSnackBar 
  ) { }
    
  onSubmit() {
    
    this.loginService.generateToken({username: this.username, password: this.password}).subscribe(
      (data:any) => {
        console.log(data);

        this.loginService.loginUser(data.token);
        this.loginService.getCurrenUser().subscribe((user:any) =>{
          this.loginService.setUser(user);
          console.log(user);

          if(this.loginService.getUserRole() == "ADMIN"){
            //vista administrador
            window.location.href = '/home-analista'
          }else if (this.loginService.getUserRole() == "CANDIDATE"){
            //vista usuario
            window.location.href = '/ofertas-laborales'
          }else{
            this.loginService.logout(); // cerrar sesion
          }
        })
      }, (error => {
        console.log(error);
        this.snack.open("Usuario o Contraseña inconrectos",'Aceptar'),{
          duration:3000
        }
      }
    )
  )   
  }

  //navigate to register
  register(){
    this.router.navigate(['/register']);
  }

  //navigate to ofertas-laborales
  viewPostulations(){
    this.router.navigate(['/ofertas-laborales']);
  }
}
