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
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class CustomerFormComponent {
  clients: any[] = [];
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

  addClient() {
    if (this.formCustomer.valid) {
      this.http
        .post<any>(
          'http://localhost:8080/api/customers',
          this.formCustomer.value
        )
        .subscribe(() => {
          this.getClients();
          this.formCustomer.reset();
          this.getClients();
          this.activeModal.close('success');
        });
    }
  }

  getClients() {
    this.http
      .get<any[]>('http://localhost:8080/api/customers')
      .subscribe((data) => {
        this.clients = data;
      });
  }

  formCustomer = this.fb.group({
    nom: ['', Validators.required],
    prenom: ['', Validators.required],
  });
}
