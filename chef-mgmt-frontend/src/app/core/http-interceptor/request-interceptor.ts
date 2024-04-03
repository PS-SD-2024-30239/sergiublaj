import {HttpInterceptorFn} from "@angular/common/http";

export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  const modifiedReq = req.clone({
    url: 'http://localhost:8777/api/' + req.url
  });

  return next(modifiedReq);
};
