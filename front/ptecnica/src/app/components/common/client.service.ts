import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ClientRequest } from './model';

export const httpOptions = 
{
  headers: new HttpHeaders
  (
    {
      'Content-Type': 'application/json',
    }
  )
}

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private HOST: string = environment.url;
  private URL_CLIENTS: string = "/api/clients";
  private URL_CLIENT: string = "/api/client";

  constructor(private httpClient: HttpClient) { }

  getList(sharedKeySearch:string):Observable<any>{
    if(sharedKeySearch){
      return this.httpClient.get(this.HOST + this.URL_CLIENTS + '/search?sharedKeySearch='+ sharedKeySearch, httpOptions)
      .pipe(catchError(this.errorHandler));
    }else{
      return this.httpClient.get(this.HOST + this.URL_CLIENTS, httpOptions)
    .pipe(catchError(this.errorHandler));
    }
  }

  getListAdvanced(optionsSearch:ClientRequest):Observable<any>{
    if(optionsSearch){
      return this.httpClient.post(this.HOST + this.URL_CLIENTS + '/search/advanced', optionsSearch)
      .pipe(catchError(this.errorHandler));
    }

    return this.httpClient.get(this.HOST + this.URL_CLIENTS, httpOptions).pipe(catchError(this.errorHandler));
  }

  addNew(clientSave:ClientRequest):Observable<any>{
    return this.httpClient.post(this.HOST + this.URL_CLIENT, clientSave, {observe: 'response'})
    .pipe(catchError(this.errorHandler));
  }

  errorHandler = (error: HttpErrorResponse) =>{
    return throwError(error.message || "server error.");
  }
}
