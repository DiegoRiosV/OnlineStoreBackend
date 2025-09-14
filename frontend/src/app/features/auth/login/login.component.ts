import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = '';
  password = '';
  hidePassword = true;
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
  this.http.post<{ token: string }>('/api/auth/login', {
    email: this.email,
    password: this.password
    }).subscribe({
      next: (res) => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/']); // home
      },
      error: (err) => {
        this.errorMessage = err.error?.message || 'Credenciales inválidas';
      }
    });
  }

  goToAdminLogin() {
    this.router.navigate(['/admin-login']);
  }
}