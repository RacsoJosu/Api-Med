package med.vol.api.domain.medico.dto;

import med.vol.api.domain.direccion.DatosDireccion;
import med.vol.api.domain.medico.entity.Especialidad;
import med.vol.api.domain.medico.entity.Medico;

public record ResponseMedico(
        Long id,

        String nombre,

        String email,

        String documento,

        Especialidad especialidad,

        String telefono,

        DatosDireccion direccion
) {
    public ResponseMedico (Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad(), medico.getTelefono(), new DatosDireccion(medico.getDireccion()));
    }
}
