import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesComponent } from './containers/clientes/clientes.component';
import { ClienteFormComponent } from './containers/cliente-form/cliente-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ClientesListaComponent } from './Components/clientes-lista/clientes-lista.component';


@NgModule({
  declarations: [
    ClientesComponent,
    ClienteFormComponent,
    ClientesListaComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    AppMaterialModule,
    ReactiveFormsModule

  ]
})
export class ClientesModule { }
