import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ProcessErrorService } from '@services/process-error.service';
import { Client } from '@models/client';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  url = `${environment.apiBaseUrl}/auth/`;
  client = new Client();

  headers = new HttpHeaders()
    .set('Content-type', 'application/json')
    .set('Accept', 'application/json');

  constructor(
    private httpClient: HttpClient,
    private processErrorService: ProcessErrorService
  ) {}

  public processAuthentication(client: Client, url?: string): Observable<Client> {
    // console.log('client: ', client);
    return this.httpClient
      .post<Client>(this.url + url, client, {
        headers: this.headers,
      })
      .pipe(
        tap((results: Client) => {
          // captured results and do nothing. There're side effects
          this.client = results;
        }),
        catchError(this.processErrorService.processError)
      );
  }
}
