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
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css',
})
export class ProductFormComponent {
  fournisseurs: any[] = [];
  selectedSupplierId: string = '';
  modalRef: NgbModalRef | undefined;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private modal: NgbModal,
    private activeModal: NgbActiveModal
  ) {}

  ngOnInit(): void {
    this.getFournisseurs();
  }

  closeModal() {
    this.modal.dismissAll();
  }

  addProduct() {
    if (this.formFournisseur.valid) {
      this.http
        .post<any>(
          'http://localhost:8080/api/products',
          this.formFournisseur.value
        )
        .subscribe(() => {
          this.formFournisseur.reset();
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
    prix: ['', Validators.required],
    quantite: ['', Validators.required],
    supplier_id: ['', Validators.required],
  });
}
