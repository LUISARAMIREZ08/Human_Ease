import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  //metodo para registrar un usuario
  public registerUser(user:any){
    return this.http.post("http://localhost:8080/auth/register",user);
  }
}
