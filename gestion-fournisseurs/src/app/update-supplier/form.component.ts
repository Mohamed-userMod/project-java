import { Component, Input, OnInit } from '@angular/core';
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
  selector: 'app-update-supplier',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class UpdateSupplierComponent implements OnInit {
  @Input() supplier: any; // Supplier passed from the parent component
  formSupplier: FormGroup;
  modalRef: NgbModalRef | undefined;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private modal: NgbModal,
    private activeModal: NgbActiveModal
  ) {
    this.formSupplier = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    console.log(this.supplier);
    if (this.supplier) {
      this.formSupplier.patchValue({
        nom: this.supplier.nom,
        prenom: this.supplier.prenom,
      });
    }
  }

  updateSupplier() {
    if (this.formSupplier.valid) {
      this.http
        .put<any>(
          `http://localhost:8080/api/suppliers/${this.supplier.id}`,
          this.formSupplier.value
        )
        .subscribe(() => {
          this.formSupplier.reset();
          this.activeModal.close('updated');
        });
    }
  }

  closeModal() {
    this.modal.dismissAll();
  }
}
