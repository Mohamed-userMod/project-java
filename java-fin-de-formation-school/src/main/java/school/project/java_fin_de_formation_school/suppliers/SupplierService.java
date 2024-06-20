package school.project.java_fin_de_formation_school.suppliers;

import org.apache.logging.log4j.util.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
import org.springframework.stereotype.Service;

import school.project.java_fin_de_formation_school.commands_lines.CommandLineEntity;
import school.project.java_fin_de_formation_school.commands_lines.CommandLineRepository;
import school.project.java_fin_de_formation_school.products.ProductEntity;
import school.project.java_fin_de_formation_school.products.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommandLineRepository commandLineRepository;

    public List<SupplierEntity> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public SupplierEntity getSupplierById(String id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public SupplierEntity saveSupplier(SupplierEntity supplier) {
        return supplierRepository.save(supplier);
    }

    public SupplierEntity updateSupplier(String id, SupplierEntity supplierEntity) {
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            SupplierEntity existingSupplier = optionalSupplier.get();

            if (supplierEntity.getNom() != null) {
                existingSupplier.setNom(supplierEntity.getNom());
            }

            if (supplierEntity.getPrenom() != null) {
                existingSupplier.setPrenom(supplierEntity.getPrenom());
            }

            return supplierRepository.save(existingSupplier);
        } else {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
    }

    // public void deleteSupplier(String id) {
    // supplierRepository.deleteById(id);
    // }

    public void deleteSupplier(String supplierId) {
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findById(supplierId);
        if (optionalSupplier.isPresent()) {
            SupplierEntity supplier = optionalSupplier.get();
            List<ProductEntity> products = productRepository.findBySupplier(supplier);
            for (ProductEntity product : products) {
                List<CommandLineEntity> commandLines = commandLineRepository.findByProduct(product);
                commandLineRepository.deleteAll(commandLines);
            }
            productRepository.deleteAll(products);
            supplierRepository.delete(supplier);
        }
        // else {
        // // throw new Error("Supplier not found with id: " + supplierId);
        // }
    }
}
