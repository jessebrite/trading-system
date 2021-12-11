import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProcessErrorService {
  constructor() {}

  processError(error: any): Observable<any> {
    let message = '';
    // client-side
    if (error.error instanceof ErrorEvent) {
      message = error.error.message;
      // server-side
    } else {
      // message = `Error Code: ${error.status}\nMessage: ${error.error.message}`;
      message = error.error.message;
    }
    // console.log(message);
    return throwError(message);
  }
}
