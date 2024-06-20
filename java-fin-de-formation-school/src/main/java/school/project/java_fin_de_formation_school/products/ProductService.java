package school.project.java_fin_de_formation_school.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.project.java_fin_de_formation_school.suppliers.SupplierEntity;
import school.project.java_fin_de_formation_school.suppliers.SupplierRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<ProductEntity> getProductsBySupplierId(String supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductEntity saveProduct(ProductDTO product) {
        ProductEntity productEntity = new ProductEntity();

        SupplierEntity supplier = new SupplierEntity();
        supplier.setId(product.supplier_id());

        productEntity.setNom(product.nom());
        productEntity.setPrix(product.prix());
        productEntity.setQuantite(product.quantite());
        productEntity.setSupplier(supplier);

        return productRepository.save(productEntity);
    }

    public ProductEntity updateProduct(String id, ProductUpdateDto productEntity) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            ProductEntity existingProduct = optionalProduct.get();

            if (productEntity.nom() != null) {
                existingProduct.setNom(productEntity.nom());
            }

            if (productEntity.prix() != 0) {
                existingProduct.setPrix(productEntity.prix());
            }

            if (productEntity.quantite() != 0) {
                existingProduct.setQuantite(productEntity.quantite());
            }

            if (productEntity.supplier_id() != null) {
                Optional<SupplierEntity> optionalSupplier = supplierRepository
                        .findById(productEntity.supplier_id());
                if (optionalSupplier.isPresent()) {
                    existingProduct.setSupplier(optionalSupplier.get());
                } else {
                    throw new RuntimeException("Supplier not found with id: " +
                            productEntity.supplier_id());
                }

            }

            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
