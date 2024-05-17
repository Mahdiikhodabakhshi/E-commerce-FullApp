import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {jwtDecode} from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  BASE_URL = "http://localhost:8080/auth";
  private token: string ='';


  register(user:any):Observable<any>{
    return this.http.post<any>(`${this.BASE_URL}/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/login`, credentials);
  }

  saveToken(token: string): void {
    this.token = token;
    localStorage.setItem('auth_token', token);
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  logout(): void {
    this.token = '';
    localStorage.removeItem('auth_token');
    // Implement any additional logout logic here
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }


  isAdmin(): boolean {
    const token = this.getToken();
    if (!token) return false; // Not authenticated
    const decodedToken: any = jwtDecode(token);
    return decodedToken.role === 'ADMIN'; // Assuming the role is 'ADMIN' for admin users
  }

  // Method to check if the user is a regular user
  isUser(): boolean {
    const token = this.getToken();
    if (!token) return false; // Not authenticated

    const decodedToken: any = jwtDecode(token);
    console.log(decodedToken);
    return decodedToken.role === 'USER'; // Assuming the role is 'USER' for regular users
  }
}
