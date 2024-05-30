import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  //generate token
  public generateToken(loginData:any){
    return this.http.post("http://localhost:8080/auth/login",loginData);
  }

  //iniciamos sesion y establecemos el token en el localStorage
  public loginUser(token:any){
    localStorage.setItem("token",token);
  }

  public isLoggedIn(){
    let tokenStr = localStorage.getItem('token');
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null){
      return false;
    }else{
      return true;
    }
  }

  //cerramos sesion y eliminamos el token del localStorage
  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //obtenemos el token
  public getToken(){
    return localStorage.getItem('token');
  }

  public setUser(user:any){
    localStorage.setItem('user',JSON.stringify(user));
  }

  public getUser(){
    if (typeof localStorage !== 'undefined') {
      let userStr = localStorage.getItem('user');
      if(userStr != null){
        return JSON.parse(userStr);
      }else{
        this.logout();
        return null;
      }
    } else {
      return null;
    }
  }

  public getUserRole(){
    let user = this.getUser();
    if (user && user.authorities && user.authorities.length > 0) {
      return user.authorities[0].authority;
    }
    return null;
  }

  public getCurrenUser(){
    return this.http.get("http://localhost:8080/auth/current-user");
  }

}
