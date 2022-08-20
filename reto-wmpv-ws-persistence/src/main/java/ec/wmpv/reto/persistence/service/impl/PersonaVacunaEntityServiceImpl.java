package ec.wmpv.reto.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.wmpv.reto.persistence.entity.PersonaVacunaEntity;
import ec.wmpv.reto.persistence.exception.PersistenceException;
import ec.wmpv.reto.persistence.repository.PersonaVacunaEntityRepository;
import ec.wmpv.reto.persistence.service.PersonaVacunaEntityService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author william.patino [Date: 19 ago. 2022]
 * @version 1.0
 **/

@Slf4j
@Component
public class PersonaVacunaEntityServiceImpl implements PersonaVacunaEntityService {

    @Autowired
    private final PersonaVacunaEntityRepository repository;

    public PersonaVacunaEntityServiceImpl(PersonaVacunaEntityRepository repository) {
        this.repository = repository;
    }

	@Override
	public void save(PersonaVacunaEntity entity) throws PersistenceException {
		try {
			this.repository.save(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistenceException("Ërror en el registro.");
		}
	}

	@Override
	public List<PersonaVacunaEntity> getVacunasByPersona(Integer id) throws PersistenceException {
		try {
			return this.repository.getVacunasByPersona(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistenceException("Ërror en la consulta.");
		}
	}

}
