package med.vol.api.domain.medico.entity;

import jakarta.persistence.*;
import lombok.*;
import med.vol.api.domain.direccion.DatosDireccion;
import med.vol.api.domain.direccion.Direccion;
import med.vol.api.domain.medico.dto.DatosRegistroMedico;

@Table(name ="medicos")
@Entity(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;

    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;


    public Medico(DatosRegistroMedico datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.telefono = datos.telefono();
        this.direccion =  new Direccion(datos.direccion());
    }

    public void actualizarDireccion(DatosDireccion direccion) {
        this.direccion =  new Direccion(direccion);

    }
}
