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
  selector: 'app-update-product',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class UpdateProductComponent implements OnInit {
  @Input() product: any;
  suppliers: any[] = [];
  formProduct: FormGroup;
  modalRef: NgbModalRef | undefined;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private modal: NgbModal,
    private activeModal: NgbActiveModal
  ) {
    this.formProduct = this.fb.group({
      nom: ['', Validators.required],
      prix: ['', Validators.required],
      quantite: ['', Validators.required],
      supplier_id: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if (this.product) {
      this.formProduct.patchValue({
        nom: this.product.nom,
        prix: this.product.prix,
        quantite: this.product.quantite,
        supplier_id: this.product.supplier.id,
      });
    }
    this.getSuppliers();
    console.log(this.product.supplier.id);
  }

  getSuppliers() {
    this.http
      .get<any[]>('http://localhost:8080/api/suppliers')
      .subscribe((data) => {
        this.suppliers = data;
        console.log(data);
      });
  }

  updateProduct() {
    if (this.formProduct.valid) {
      this.http
        .put<any>(
          `http://localhost:8080/api/products/${this.product.id}`,
          this.formProduct.value
        )
        .subscribe(() => {
          console.log(this.formProduct.value);
          // this.formProduct.reset();
          this.activeModal.close('updated');
        });
    }
  }

  closeModal() {
    this.modal.dismissAll();
  }
}
