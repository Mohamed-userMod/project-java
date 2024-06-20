package school.project.java_fin_de_formation_school.suppliers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<SupplierEntity> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierEntity getSupplierById(@PathVariable String id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public SupplierEntity createSupplier(@RequestBody SupplierEntity supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @PutMapping("/{id}")
    public SupplierEntity updateSupplier(@PathVariable String id, @RequestBody SupplierEntity supplierEntity) {
        return supplierService.updateSupplier(id, supplierEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable String id) {
        supplierService.deleteSupplier(id);
    }
}
