import { Component } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ClientesService } from '../../services/clientes.service';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from '../../model/cliente';



@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrl: './cliente-form.component.scss'
})
export class ClienteFormComponent {

  form: UntypedFormGroup;

  constructor( private formBuilder: UntypedFormBuilder,
    private service: ClientesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
    ){
      this.form = this.formBuilder.group( {
        id:[''],
        nome:[''],
        telefone:['']
      } );
  }

    ngOnInit(): void {

      const cliente: Cliente = this.route.snapshot.data['cliente'];
      this.form.setValue({
        id: cliente.id,
        nome:  cliente.nome,
        telefone: cliente.telefone
      });


    }

     onSubmit(){
      this.service.save(this.form.value as Cliente).subscribe({
        next: () => this.onSuccess(),
        error: () => this.onError()
      });
    }
    /*onSubmit() {
      this.service.save(this.form.value).subscribe( result => this.onSuccess, error => this.onError);
    } */
    onCancelar(){
      this.location.back();
    }

    private onSuccess(){
      this.snackBar.open('Cliente salvo com sucesso.', '',{ duration: 5000 });
      this.onCancelar();
    }

    private onError(){
      this.snackBar.open('Erro ao salvar cliente.', '',{ duration: 5000 });
    }
}
