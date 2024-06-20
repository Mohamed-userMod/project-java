package school.project.java_fin_de_formation_school.orders;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.project.java_fin_de_formation_school.customers.CustomerEntity;
import school.project.java_fin_de_formation_school.customers.CustomerRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity saveOrder(OrderDto orderDTO) {
        OrderEntity orderEntity = new OrderEntity();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setid(orderDTO.customer_id());

        orderEntity.setDate(orderDTO.date());
        orderEntity.setCustomer(customerEntity);

        orderEntity = orderRepository.save(orderEntity);

        return orderEntity;
    }

    public OrderEntity updateOrder(String id, OrderEntity orderEntity) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            OrderEntity existingOrder = optionalOrder.get();

            if (orderEntity.getDate() != null) {
                existingOrder.setDate(orderEntity.getDate());
            }

            if (orderEntity.getCustomer() != null && orderEntity.getCustomer().getid() != null) {
                Optional<CustomerEntity> customer = customerRepository.findById(orderEntity.getCustomer().getid());
                customer.ifPresent(existingOrder::setCustomer);
            }

            return orderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    public List<OrderEntity> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
