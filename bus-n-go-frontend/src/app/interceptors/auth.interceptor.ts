import {HttpInterceptorFn} from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const jwt = sessionStorage.getItem('jwt');
  if (jwt && jwt.length > 0) {
    const newReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${sessionStorage.getItem('jwt')}`,
      },
    });
    return next(newReq);
  }
  return next(req);
};
