package ec.wmpv.reto.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.wmpv.reto.persistence.entity.PersonaVacunaEntity;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Repository
public interface PersonaVacunaEntityRepository extends CrudRepository<PersonaVacunaEntity, Integer> {
	
	@Query("select c from PersonaVacunaEntity c where c.idPersona = :id")
	List<PersonaVacunaEntity> getVacunasByPersona(@Param("id") Integer id);
	
}
