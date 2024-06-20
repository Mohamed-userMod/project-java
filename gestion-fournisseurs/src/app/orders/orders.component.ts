import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CustomerFormComponent } from '../form-customers/form.component';
import { OrdersFormComponent } from '../form-orders/form.component';
import { CommandLineFormComponent } from '../form-command-line/form.component';

@Component({
  selector: 'app-gestion-commandes',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class GestionCommandesComponent implements OnInit {
  commandes: any[] = [];
  lignesCommande: any[] = [];
  clients: any[] = [];

  constructor(private http: HttpClient, private modal: NgbModal) {}

  ngOnInit(): void {
    this.getCommandes();
    this.getClients();
    this.getLignesCommande();
  }

  onSearch(searchTerm: string) {
    if (searchTerm === '') {
      this.getClients();
    } else {
      this.http
        .get<any[]>(
          `http://localhost:8080/api/customers/search?term=${searchTerm}`
        )
        .subscribe((data) => {
          this.clients =
            data == null
              ? [
                  {
                    id: `Aucun client ne correspond à "${searchTerm}".`,
                    nom: '',
                    prenom: '',
                  },
                ]
              : data;
        });
    }
  }

  openCommandLineModal() {
    const modalRef = this.modal.open(CommandLineFormComponent);
    modalRef.componentInstance.title = 'Ajouter un produit';
    modalRef.result.then((result) => {
      if (result === 'success') {
        this.getLignesCommande();
      }
    });
  }

  openClientsModal() {
    const modalRef = this.modal.open(CustomerFormComponent);
    modalRef.componentInstance.title = 'Ajouter un produit';
    modalRef.result.then((result) => {
      if (result === 'success') {
        this.getClients();
      }
    });
  }

  openOrdersModal() {
    const modalRef = this.modal.open(OrdersFormComponent);
    modalRef.componentInstance.title = 'Ajouter un produit';
    modalRef.result.then((result) => {
      if (result === 'success') {
        this.getCommandes();
      }
    });
  }

  getCommandes() {
    this.http
      .get<any[]>('http://localhost:8080/api/orders')
      .subscribe((data) => {
        this.commandes = data;
      });
  }

  getLignesCommande() {
    this.http
      .get<any[]>('http://localhost:8080/api/command-line')
      .subscribe((data) => {
        this.lignesCommande = data;
        console.log(data);
      });
  }

  getClients() {
    this.http
      .get<any[]>('http://localhost:8080/api/customers')
      .subscribe((data) => {
        this.clients = data;
      });
  }
}
