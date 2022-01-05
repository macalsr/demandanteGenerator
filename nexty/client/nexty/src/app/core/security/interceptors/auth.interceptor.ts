import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '@core/services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.authService.token;

    if (token && request.url.indexOf('https://viacep.com.br/ws/') < 0) {
      const cloned = request.clone({
        headers: request.headers.set('Authorization', 'Bearer '.concat(token)),
      });

      return next.handle(cloned);
    }

    return next.handle(request);
  }
}
