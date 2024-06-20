package school.project.java_fin_de_formation_school.orders;

import jakarta.validation.constraints.NotEmpty;

public record OrderDto(
        @NotEmpty String date,
        @NotEmpty String customer_id) {
}