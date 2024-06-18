package med.vol.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.direccion.DatosDireccion;

public record UpdateMedico(

        @NotNull
        Long id,

        String nombre,

        @Pattern(regexp = "\\d{4,6}")
        String documento,

        @Valid
        DatosDireccion direccion
) {
}
