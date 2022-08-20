package ec.wmpv.reto.persistence.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ec.wmpv.reto.persistence.entity.PersonaEntity;
import ec.wmpv.reto.persistence.exception.PersistenceException;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
public interface PersonaEntityService {
	
	/**
	 * Obtiene la persona
	 * @param user
	 * @return PersonaEntity
	 * @throws PersistenceException
	 */
	PersonaEntity getPersona(String user) throws PersistenceException;
	
	/**
	 * Obtiene personas
	 * @param pageable
	 * @param id
	 * @param cedula
	 * @return
	 * @throws PersistenceException
	 */
    Page<PersonaEntity> getPersonas(Pageable pageable, Integer id, String cedula) throws PersistenceException;
    
    /**
     * Obtiene personas seg&uacute;n filtro
     * @param pageable
     * @param vacunado
     * @param tipo
     * @param fechaIni
     * @param fechaEnd
     * @return
     * @throws PersistenceException
     */
    Page<PersonaEntity> getPersonasFilter(Pageable pageable, String vacunado, String tipo, Date fechaIni, Date fechaEnd) throws PersistenceException;
    
    /**
     * Registra una persona
     * @param entity
     * @throws PersistenceException
     */
    void save(PersonaEntity entity) throws PersistenceException;
}
