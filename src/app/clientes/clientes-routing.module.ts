import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesComponent } from './containers/clientes/clientes.component';
import { ClienteFormComponent } from './containers/cliente-form/cliente-form.component';
import { clienteResolver } from './guards/cliente.resolver';

const routes: Routes = [
  { path: '', component: ClientesComponent},
  { path: 'novo', component: ClienteFormComponent, resolve: { cliente: clienteResolver } },
  { path: 'edit/:id', component: ClienteFormComponent, resolve: { cliente: clienteResolver } }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
