package school.project.java_fin_de_formation_school.products;

import jakarta.validation.constraints.NotEmpty;

public record ProductDTO(@NotEmpty String nom, @NotEmpty double prix, @NotEmpty int quantite,
                @NotEmpty String supplier_id) {

}
