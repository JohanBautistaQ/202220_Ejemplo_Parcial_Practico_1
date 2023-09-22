package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

   @Transactional
	public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException {
		log.info("Inicia proceso de creación de un especialidad");
      
		if(especialidad.getDescripcion().length()>=10) {
			throw new IllegalOperationException("La descripcion debe tener al menos 10 caracteres");
	    }
		return especialidadRepository.save(especialidad);
	}

}
