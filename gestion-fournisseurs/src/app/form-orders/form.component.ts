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
  selector: 'app-orders-form',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class OrdersFormComponent {
  orders: any[] = [];
  modalRef: NgbModalRef | undefined;
  clients: any[] = [];

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private modal: NgbModal,
    private activeModal: NgbActiveModal
  ) {}

  closeModal() {
    this.modal.dismissAll();
  }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.http
      .get<any[]>('http://localhost:8080/api/customers')
      .subscribe((data) => {
        this.clients = data;
      });
  }

  addOrder() {
    if (this.formOrder.valid) {
      this.http
        .post<any>('http://localhost:8080/api/orders', this.formOrder.value)
        .subscribe(() => {
          this.getOrders();
          console.log(this.formOrder.value);
          this.formOrder.reset();
          this.getOrders();
          this.activeModal.close('success');
        });
    }
  }

  getOrders() {
    this.http
      .get<any[]>('http://localhost:8080/api/orders')
      .subscribe((data) => {
        this.orders = data;
      });
  }

  formOrder = this.fb.group({
    date: ['', Validators.required],
    customer_id: ['', Validators.required],
  });
}
