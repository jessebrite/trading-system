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
      // console.log(`the error message: ${error.message}`);
      console.log("error: ", error);
      message = error.message;
    }
    // console.log(message);
    return throwError(message);
  }
}
