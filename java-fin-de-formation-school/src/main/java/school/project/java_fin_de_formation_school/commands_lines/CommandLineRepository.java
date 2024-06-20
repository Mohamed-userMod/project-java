package school.project.java_fin_de_formation_school.commands_lines;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.project.java_fin_de_formation_school.products.ProductEntity;

@Repository
public interface CommandLineRepository extends JpaRepository<CommandLineEntity, String> {

    List<CommandLineEntity> findByOrderId(String orderId);

    List<CommandLineEntity> findByProduct(ProductEntity product);
}
