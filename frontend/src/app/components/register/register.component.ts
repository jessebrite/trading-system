import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { Client } from '@models/client';
import { AuthenticationService } from '@services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  client: Client = new Client();
  clientSent = false;
  submitted = false;
  url = 'register';
  successMessage = '';
  errorMessage = '';

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit(): void {}

  onSubmit(form: NgForm): void {
    this.submitted = true;
    if (form.valid) {
      this.doRegistration(this.client, this.url);
    }
  }
  public doRegistration(client: Client, url: string): void {
    this.authenticationService.processAuthentication(client, url).subscribe(
      (data: Client) => {
        console.log('data: ', data), (this.client = data);
        this.successMessage = data.message;
        if (this.successMessage.length > 0) {
          this.clientSent = true;
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 1000);
        }
      },
      // Keep this! Every other server-side error is captured here
      (error) => {
        // console.log('error:	', error)
        this.errorMessage = error;
      }
    );
  }
}
