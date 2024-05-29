import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }
  //Get
  public getAPI(url:string): Observable<any> {
    return this.http.get(`http://localhost:8080/${url}`).pipe(
    );
  }

  //Post 
  public postAPI(url:string, body:any): Observable<any> {
    return this.http.post(`http://localhost:8080/${url}`, body).pipe(
    );
  }

  //Put
  public putAPI(url:string, body:any): Observable<any> {
    return this.http.put(`http://localhost:8080/${url}`, body).pipe(
    );
  }
}
