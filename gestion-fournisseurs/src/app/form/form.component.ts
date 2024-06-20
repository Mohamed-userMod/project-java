import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {
  NgbActiveModal,
  NgbModal,
  NgbModalRef,
} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  fournisseurs: any[] = [];
  modalRef: NgbModalRef | undefined;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private modal: NgbModal,
    private activeModal: NgbActiveModal
  ) {}

  closeModal() {
    this.modal.dismissAll();
  }

  addFournisseur() {
    if (this.formFournisseur.valid) {
      this.http
        .post<any>(
          'http://localhost:8080/api/suppliers',
          this.formFournisseur.value
        )
        .subscribe(() => {
          this.formFournisseur.reset();
          // this.closeModal();
          this.activeModal.close('added');
        });
    }
  }

  getFournisseurs() {
    this.http
      .get<any[]>('http://localhost:8080/api/suppliers')
      .subscribe((data) => {
        this.fournisseurs = data;
      });
  }

  formFournisseur = this.fb.group({
    nom: ['', Validators.required],
    prenom: ['', Validators.required],
  });
}
