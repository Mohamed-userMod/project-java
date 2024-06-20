package school.project.java_fin_de_formation_school.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerEntity> getAllClients() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/search")
    public List<CustomerEntity> searchCustomers(@RequestParam String term) {
        return customerService.searchCustomers(term);
    }

    @GetMapping("/{id}")
    public CustomerEntity getClientById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerEntity createClient(@RequestBody CustomerEntity customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public CustomerEntity updateCustomer(@PathVariable String id, @RequestBody CustomerEntity customerEntity) {
        return customerService.updateCustomer(id, customerEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

}
