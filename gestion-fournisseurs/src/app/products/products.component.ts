import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductFormComponent } from '../product-form/product-form.component';
import { CommonModule } from '@angular/common';
import { UpdateProductComponent } from '../update-product/form.component';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  products: any[] = [];
  newProduct: any = {};

  constructor(private http: HttpClient, private modal: NgbModal) {}

  ngOnInit(): void {
    this.getProducts();
  }

  openModal() {
    const modalRef = this.modal.open(ProductFormComponent);
    modalRef.componentInstance.title = 'Ajouter un produit';
    modalRef.result.then((result) => {
      if (result === 'added') {
        this.getProducts();
      }
    });
  }

  getProducts() {
    this.http
      .get<any[]>('http://localhost:8080/api/products')
      .subscribe((data) => {
        this.products = data;
      });
  }

  addProduct() {
    this.http
      .post<any>('http://localhost:8080/api/products', this.newProduct)
      .subscribe(() => {
        this.getProducts();
        this.newProduct = {};
      });
  }

  deleteProduct(id: string) {
    this.http
      .delete<any>('http://localhost:8080/api/products/' + id)
      .subscribe(() => {
        this.getProducts();
      });
  }

  openUpdateModal(product: any) {
    const modalRef = this.modal.open(UpdateProductComponent);
    modalRef.componentInstance.product = product;
    modalRef.result.then((result) => {
      if (result === 'updated') {
        this.getProducts();
      }
    });
  }
}
