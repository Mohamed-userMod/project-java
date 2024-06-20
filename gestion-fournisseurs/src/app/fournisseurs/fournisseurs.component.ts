import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormComponent } from '../form/form.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateSupplierComponent } from '../update-supplier/form.component';

@Component({
  selector: 'app-fournisseurs',
  standalone: true,
  templateUrl: './fournisseurs.component.html',
  styleUrls: ['./fournisseurs.component.css'],
  imports: [HttpClientModule, FormsModule, CommonModule, FormComponent],
})
export class FournisseursComponent implements OnInit {
  fournisseurs: any[] = [];
  nouveauFournisseur: any = {};

  constructor(private http: HttpClient, private modal: NgbModal) {}

  ngOnInit(): void {
    this.getFournisseurs();
  }

  openModal() {
    const modalRef = this.modal.open(FormComponent);
    modalRef.result.then((result) => {
      console.log('Modal closed');
      if (result === 'added') {
        this.getFournisseurs();
      }
    });
  }

  getFournisseurs() {
    this.http
      .get<any[]>('http://localhost:8080/api/suppliers')
      .subscribe((data) => {
        this.fournisseurs = data;
      });
  }

  addFournisseur() {
    this.http
      .post<any>('http://localhost:8080/api/suppliers', this.nouveauFournisseur)
      .subscribe(() => {
        this.getFournisseurs();
        this.nouveauFournisseur = {}; // Réinitialise le formulaire d'ajout
      });
  }

  deleteFournisseur(id: string) {
    this.http
      .delete<any>('http://localhost:8080/api/suppliers/' + id)
      .subscribe(() => {
        this.getFournisseurs();
      });
  }

  openUpdateModal(supplier: any) {
    console.log(supplier);
    const modalRef = this.modal.open(UpdateSupplierComponent);
    modalRef.componentInstance.supplier = supplier;
    modalRef.result.then((result) => {
      console.log('Modal closed');
      if (result === 'updated') {
        this.getFournisseurs();
      }
    });
  }
}
