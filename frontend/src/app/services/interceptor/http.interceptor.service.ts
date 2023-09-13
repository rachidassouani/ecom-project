import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationResponse } from 'src/app/models/AuthenticationResponse';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const loggedUser = localStorage.getItem('user');   
      if (loggedUser) {
        const authReponse: AuthenticationResponse = JSON.parse(loggedUser);
        const token = authReponse.token;
        if (token) {
          const authReq = req.clone({
            headers: new HttpHeaders({Authorization: 'Bearer ' + token})
          });
          return next.handle(authReq);
        }
      }
    return next.handle(req);
  }
}
