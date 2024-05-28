import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { Router} from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import {UserService} from '../../services/user.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export default class RegisterComponent {

  registerForm!: FormGroup;

  constructor (
    private router: Router,
    private formBuilder: FormBuilder, 
    private snack: MatSnackBar,
    private userService: UserService,
    private loginService: LoginService
    ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: new FormControl('',[Validators.required]),
      username: new FormControl('',[Validators.required]),
      phone: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required]),
      confirmPassword: new FormControl('',[Validators.required]),
      lastName: new FormControl('',[Validators.required]),
      cardId: new FormControl('',[Validators.required]),
      birthDate: new FormControl('',[Validators.required]),
      email: new FormControl('',[Validators.required,Validators.email]),
      address: new FormControl('',[Validators.required]),
      terms: new FormControl(false,[Validators.requiredTrue])
    });
  }

  onSubmit(){
    console.log('Formulario de registro: ',this.registerForm.value);
    console.log('Formulario de registro válido: ',this.registerForm.valid);
    console.log('Errores del formulario: ', this.registerForm.errors);
    console.log('Errores en los campos del formulario: ', this.registerForm.controls);
    if (this.registerForm.valid){
      const formData = this.registerForm.value;
      if(formData.password != formData.confirmPassword){
        this.snack.open('Las contraseñas no coinciden','Aceptar', {
          duration:3000
        });
        return;
    }
    this.userService.registerUser(formData).subscribe(
      
      (response:any) => {
        console.log('Usuario registrado con éxito',response);
        this.snack.open('Usuario registrado con éxito','Aceptar',{
          duration:3000
        });
        this.loginService.loginUser(response.token);
        this.router.navigate(['/usuario-cuenta']);
      },
      error => {
        console.error('Error al registrar el usuario',error);
        this.snack.open('Error al registrar el usuario','Aceptar',{
          duration:3000
        });
      }
    );
    }else{
      this.snack.open('Por favor, complete el formulario correctamente','Aceptar',{
        duration:3000
      });
    }
  }
}
