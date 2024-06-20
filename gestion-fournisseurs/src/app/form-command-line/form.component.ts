import { Component, OnInit } from '@angular/core';
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
  selector: 'app-command-line-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule, FormsModule, CommonModule],
})
export class CommandLineFormComponent implements OnInit {
  commandLines: any[] = [];
  modalRef: NgbModalRef | undefined;
  orders: any[] = [];
  products: any[] = [];

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private modal: NgbModal,
    private activeModal: NgbActiveModal
  ) {}

  ngOnInit(): void {
    this.getOrders();
    this.getProducts();
  }

  closeModal() {
    this.modal.dismissAll();
  }

  getOrders() {
    this.http
      .get<any[]>('http://localhost:8080/api/orders')
      .subscribe((data) => {
        this.orders = data;
        console.log(this.orders);
      });
  }

  getProducts() {
    this.http
      .get<any[]>('http://localhost:8080/api/products')
      .subscribe((data) => {
        this.products = data;
        console.log(this.products);
      });
  }

  addCommandLine() {
    if (this.formCommandLine.valid) {
      this.http
        .post<any>(
          'http://localhost:8080/api/command-line',
          this.formCommandLine.value
        )
        .subscribe(() => {
          this.getCommandLines();
          this.formCommandLine.reset();
          this.getCommandLines();
          this.activeModal.close('success');
        });
    }
  }

  getCommandLines() {
    this.http
      .get<any[]>('http://localhost:8080/api/command-line')
      .subscribe((data) => {
        this.commandLines = data;
      });
  }

  formCommandLine = this.fb.group({
    order_id: ['', Validators.required],
    product_id: ['', Validators.required],
    quantity: ['', Validators.required],
  });
}
