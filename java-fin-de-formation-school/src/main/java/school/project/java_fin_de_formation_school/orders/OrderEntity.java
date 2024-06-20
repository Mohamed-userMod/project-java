package school.project.java_fin_de_formation_school.orders;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import school.project.java_fin_de_formation_school.commands_lines.CommandLineEntity;
import school.project.java_fin_de_formation_school.customers.CustomerEntity;

@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String date;

    @ManyToOne
    private CustomerEntity customer;

    @JsonBackReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CommandLineEntity> orderLines;

    public String getId() {
        return id;
    }

    public List<CommandLineEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<CommandLineEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
