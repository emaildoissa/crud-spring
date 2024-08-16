import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable } from 'rxjs';

import { Cliente } from '../../model/cliente';
import { ClientesService } from '../../services/clientes.service';
import { Action } from 'rxjs/internal/scheduler/Action';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';



@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.scss'
})
export class ClientesComponent implements OnInit {


onDelete($event: Event) {
throw new Error('Method not implemented.');
}

  clientes: Observable<Cliente[]>;




  constructor(
    private clientesService: ClientesService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    public dialog: MatDialog


   ) {
    this.clientes = this.clientesService.list();


  }
  ngOnInit(): void {
    // TODO document why this method 'ngOnInit' is empty


  }
  onAdd() {
   this.router.navigate( ['novo'], {relativeTo: this.route} );
  }

  onEdit(cliente: Cliente){
    this.router.navigate(['edit', cliente.id ], {relativeTo: this.route })
  }

  onRemove(cliente: Cliente){
    this.clientesService.remove(cliente.id).subscribe(
      () => {
        this.snackBar.open('Cliente removido com sucesso', 'X', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        })
      }
    )
    this.refresh();
  }

  refresh() {
    this.clientes = this.clientesService.list();
  }

  onError(errorMsg: string){
    this.dialog.open(ErrorDialogComponent,{
      data: errorMsg
    });
  }
}
