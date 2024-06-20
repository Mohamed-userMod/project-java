package school.project.java_fin_de_formation_school.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/supplier/{supplierId}")
    public List<ProductEntity> getProductsBySupplierId(@PathVariable String supplierId) {
        return productService.getProductsBySupplierId(supplierId);
    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductEntity createProduct(@RequestBody ProductDTO product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable String id, @RequestBody ProductUpdateDto productEntity) {
        return productService.updateProduct(id, productEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
