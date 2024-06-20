package school.project.java_fin_de_formation_school.products;

import java.util.List;

import org.apache.logging.log4j.util.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import school.project.java_fin_de_formation_school.suppliers.SupplierEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("SELECT p FROM ProductEntity p WHERE p.supplier.id = :supplierId")
    List<ProductEntity> findBySupplierId(@Param("supplierId") String supplierId);

    List<ProductEntity> findBySupplier(SupplierEntity supplier);
}
