import {HttpHeaders, HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {CookieService} from "ngx-cookie-service";


export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  const cookieService = inject(CookieService);
  const jwtToken = cookieService.get('jwt-token');
  const headers = new HttpHeaders({
    'Authorization': 'Bearer ' + jwtToken
  });

  const modifiedReq = req.clone({
    url: 'http://localhost:8777/api/' + req.url,
    withCredentials: true,
    headers
  });

  return next(modifiedReq);
};
