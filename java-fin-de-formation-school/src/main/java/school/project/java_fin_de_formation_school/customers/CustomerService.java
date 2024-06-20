package school.project.java_fin_de_formation_school.customers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import school.project.java_fin_de_formation_school.orders.OrderEntity;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository CustomerRepository;

    public List<CustomerEntity> getAllCustomer() {
        return CustomerRepository.findAll();
    }

    public CustomerEntity getCustomerById(String id) {
        return CustomerRepository.findById(id).orElse(null);
    }

    public CustomerEntity createCustomer(CustomerEntity CustomerEntity) {
        return CustomerRepository.save(CustomerEntity);
    }

    public List<CustomerEntity> searchCustomers(String term) {
        System.out.println(term);
        return CustomerRepository.findByNomContaining(term);
    }

    public CustomerEntity updateCustomer(String id, CustomerEntity customerEntity) {
        Optional<CustomerEntity> optionalCustomer = CustomerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            CustomerEntity existingCustomer = optionalCustomer.get();

            if (customerEntity.getNom() != null) {
                existingCustomer.setNom(customerEntity.getNom());
            }
            if (customerEntity.getPrenom() != null) {
                existingCustomer.setPrenom(customerEntity.getPrenom());
            }

            return CustomerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    public void deleteCustomer(String id) {
        CustomerRepository.deleteById(id);
    }
}
