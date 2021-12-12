// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  apiBaseUrl: 'http://localhost:8081/api/v1',
  // clientServiceBaseUrl: 'http://localhost:8081/api/v1',
  orderServiceBaseUrl: 'http://localhost:8082/api/v1',
  transactionServiceBaseUrl: 'http://localhost:8083/api/v1',
  loggingServiceBaseUrl: 'http://localhost:8084/api/v1',
  user_key: 'auth-user',
  token_key: 'auth-token',
  production: false,
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
