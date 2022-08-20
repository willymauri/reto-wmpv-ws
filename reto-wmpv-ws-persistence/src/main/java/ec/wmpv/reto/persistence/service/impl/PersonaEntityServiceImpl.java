package ec.wmpv.reto.persistence.service.impl;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import ec.wmpv.reto.persistence.entity.PersonaEntity;
import ec.wmpv.reto.persistence.exception.PersistenceException;
import ec.wmpv.reto.persistence.repository.PersonaEntityRepository;
import ec.wmpv.reto.persistence.service.PersonaEntityService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Slf4j
@Component
public class PersonaEntityServiceImpl implements PersonaEntityService {

    @Autowired
    private final PersonaEntityRepository repository;

    public PersonaEntityServiceImpl(PersonaEntityRepository repository) {
        this.repository = repository;
    }

	@Override
	public PersonaEntity getPersona(String user) throws PersistenceException {
		try {
			PersonaEntity entity = this.repository.getPersona(user);
			if(Objects.isNull(entity))
				throw new PersistenceException("Persona no encontrada.");
			return entity;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistenceException("Ërror al buscar la persona.");
		}
	}

	@Override
	public Page<PersonaEntity> getPersonas(Pageable pageable, Integer id, String cedula) throws PersistenceException {
		try {
			return this.repository.getPersonas(pageable, id, cedula);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistenceException("Ërror en la consulta.");
		}
	}

	@Override
	public Page<PersonaEntity> getPersonasFilter(Pageable pageable, String vacunado, String tipo, Date fechaIni,
			Date fechaEnd) throws PersistenceException {
		try {
			return this.repository.getPersonasFilter(pageable, vacunado, tipo, fechaIni, fechaEnd);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistenceException("Ërror en la consulta.");
		}
	}

	@Override
	public void save(PersonaEntity entity) throws PersistenceException {
		try {
			this.repository.save(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistenceException("Ërror en el registro.");
		}
	}

}
