package med.vol.api.domain.medico;

import med.vol.api.domain.medico.dto.DatosRegistroMedico;
import med.vol.api.domain.medico.dto.ResponseMedico;
import med.vol.api.domain.medico.dto.UpdateMedico;
import med.vol.api.domain.medico.entity.Medico;
import med.vol.api.domain.medico.dto.ListadoMedico;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    public Page<ListadoMedico> getAll(Pageable pagination){
        return this.repository.findByActivoTrue(pagination).map(ListadoMedico::new);
    }

    public ResponseMedico update(UpdateMedico updateMedico){
        var medico = this.repository.findById(updateMedico.id());

        if (medico.isPresent()){

            if (!updateMedico.nombre().equalsIgnoreCase("")){
                medico.get().setNombre(updateMedico.nombre());
            }
            if (!updateMedico.documento().equalsIgnoreCase("")){
                medico.get().setDocumento(updateMedico.documento());
            }
            if (updateMedico.direccion() != null){
                medico.get().actualizarDireccion(updateMedico.direccion());
            }



            return medico.map(ResponseMedico::new).get();

        }



        return null;
    }

    public ResponseMedico AddMedico(DatosRegistroMedico datos){

        Medico newMedico = new Medico(datos);


        var medico = this.repository.save(newMedico);


        return new ResponseMedico(medico) ;

    }

    public ResponseMedico getOne(Long id){
        Medico medico = this.repository.getReferenceById(id);

        if (medico == null){
            return  null;
        }

        return new ResponseMedico(medico);


    }

    public void delete(Long id){
        Medico medico = this.repository.getReferenceById(id);
        medico.setActivo(false);


    }




}
