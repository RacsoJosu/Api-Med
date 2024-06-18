package med.vol.api.domain.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Login(
        @NotBlank(message = "Ingrese un valor para username ")
        String username,
        @NotBlank(message = "Ingrese un valor para clave")
        String clave
) {
}
