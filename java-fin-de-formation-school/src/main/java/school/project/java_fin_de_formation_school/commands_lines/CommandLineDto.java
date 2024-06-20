package school.project.java_fin_de_formation_school.commands_lines;

import jakarta.validation.constraints.NotEmpty;

public record CommandLineDto(
                @NotEmpty String order_id,
                @NotEmpty String product_id,
                @NotEmpty int quantity) {
}
