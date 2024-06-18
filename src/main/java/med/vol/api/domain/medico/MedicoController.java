package med.vol.api.domain.medico;

import jakarta.validation.Valid;
import med.vol.api.domain.medico.dto.DatosRegistroMedico;
import med.vol.api.domain.medico.dto.ListadoMedico;
import med.vol.api.domain.medico.dto.ResponseMedico;
import med.vol.api.domain.medico.dto.UpdateMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RequestMapping("/medicos")
@RestController
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity<ResponseMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder){
        var medico = this.service.AddMedico(datos);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.id()).toUri();
        return ResponseEntity.created(url).body(medico);
    }




    @GetMapping
    public Page<ListadoMedico> findAll(@PageableDefault(size = 2) Pageable pagination){
        return this.service.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        var medico = this.service.getOne(id);
        return ResponseEntity.ok(medico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateMedico updateMedico){
        var medico =this.service.update(updateMedico);
        if (medico == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }





}
