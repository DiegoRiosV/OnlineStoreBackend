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

  loggedClient: any = null; // aquí guardamos el cliente validado

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.get<any[]>('http://localhost:8080/clients/all').subscribe({
      next: (clients) => {
        const client = clients.find(c => c.mail === this.email && c.password === this.password);
        if (client) {
          this.loggedClient = client;
          this.errorMessage = '';
          alert(`Bienvenido, ${client.name}`);
          this.router.navigate(['/']);
        } else {
          this.errorMessage = 'Credenciales inválidas';
        }
      },
      error: () => {
        this.errorMessage = 'Error al conectar con el servidor';
      }
    });
  }

  goToAdminLogin() {
    this.router.navigate(['/admin-login']);
  }
}
