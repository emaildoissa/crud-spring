import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Cliente } from '../model/cliente';


@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  private readonly API = 'api/clientes';

  constructor(private httpClient: HttpClient) {}


  list() {
    return this.httpClient.get<Cliente[]>(this.API)
    .pipe(
      first(),
      //delay(3000),
      //tap(clientes => console.log(clientes))
    );

  }
  save (record: Partial<Cliente>){
    console.log(record);
    if (record.id){
      //console.log('update');
      return this.update(record);
    }
    //console.log('create');
    return this.create(record);
  }

  loadById(id: string){
   return this.httpClient.get<Cliente>(`${this.API}/${id}`);
  }

  private create(record: Partial<Cliente>){
    return this.httpClient.post<Cliente>(this.API, record).pipe(first());
  }

  private update(record: Partial<Cliente>){
    return this.httpClient.put<Cliente>(`${this.API}/${record.id}`, record).pipe(first());
  }

  remove(id: string){
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

}
