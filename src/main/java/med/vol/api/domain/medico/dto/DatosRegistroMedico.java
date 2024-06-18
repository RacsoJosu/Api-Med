package med.vol.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.direccion.DatosDireccion;
import med.vol.api.domain.medico.entity.Especialidad;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        @Valid
        Especialidad especialidad,

        @NotBlank
        @Pattern(regexp = "\\+504[389]\\d{1}(\\d{2}){3}")
        String telefono,
        @NotNull
        @Valid
        DatosDireccion direccion

) {
}
