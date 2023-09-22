package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoRepository medicoRepository;

	@Autowired
    private EspecialidadRepository especialidadRepository;

   @Transactional
	public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una especialidad al medico con id = {0}", medicoId);
		Optional<MedicoEntity> MedicoEntity = medicoRepository.findById(medicoId);
		Optional<EspecialidadEntity> EspecialidadEntity = especialidadRepository.findById(especialidadId);

		if (MedicoEntity.isEmpty())
			throw new EntityNotFoundException("No se encontró el medico con id = {0}"+ medicoId);

		if (EspecialidadEntity.isEmpty())
			throw new EntityNotFoundException("No se encontró el especialidad con id = {0}"+ especialidadId);

		MedicoEntity.get().getEspecialidades().add(EspecialidadEntity.get());
		log.info("Termina proceso de asociarle una especialidad al medico con id = {0}", medicoId);
		return EspecialidadEntity.get();
	}

}
