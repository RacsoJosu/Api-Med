package med.vol.api.domain.medico.dto;

import med.vol.api.domain.medico.entity.Medico;

public record ListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String telefono,
        String email
) {

    public ListadoMedico(Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEspecialidad().toString(), medico.getTelefono(), medico.getEmail());
    }
}
