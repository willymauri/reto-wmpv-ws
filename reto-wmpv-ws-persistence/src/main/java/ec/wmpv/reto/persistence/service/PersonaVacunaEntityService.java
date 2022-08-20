package ec.wmpv.reto.persistence.service;

import java.util.List;

import ec.wmpv.reto.persistence.entity.PersonaVacunaEntity;
import ec.wmpv.reto.persistence.exception.PersistenceException;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
public interface PersonaVacunaEntityService {
	
	/**
	 * Registra la vacuna de la persona
	 * @param entity
	 * @throws PersistenceException
	 */
	void save(PersonaVacunaEntity entity) throws PersistenceException;
	
	/**
	 * Obtiene las vacunas por persona
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
    List<PersonaVacunaEntity> getVacunasByPersona(Integer id) throws PersistenceException;

}
