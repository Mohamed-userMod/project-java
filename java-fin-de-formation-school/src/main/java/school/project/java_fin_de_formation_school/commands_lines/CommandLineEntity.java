package school.project.java_fin_de_formation_school.commands_lines;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import school.project.java_fin_de_formation_school.orders.OrderEntity;
import school.project.java_fin_de_formation_school.products.ProductEntity;

@Entity
@Table(name = "CommandesLines")
public class CommandLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCommandLine;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    private ProductEntity product;

    private int quantity;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getIdCommandLine() {
        return idCommandLine;
    }

    public void setIdCommandLine(String idCommandLine) {
        this.idCommandLine = idCommandLine;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
