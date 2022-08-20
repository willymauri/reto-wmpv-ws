package ec.wmpv.reto.persistence.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.wmpv.reto.persistence.entity.PersonaEntity;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Repository
public interface PersonaEntityRepository extends CrudRepository<PersonaEntity, Integer> {
    
	@Query("select c from PersonaEntity c where c.usuario = :user")
	PersonaEntity getPersona(@Param("user") String user);
	
    @Query("select c from PersonaEntity c where c.idPersona = COALESCE(:id, c.idPersona) and c.cedula = COALESCE(:cedula, c.cedula)")
    Page<PersonaEntity> getPersonas(Pageable pageable, @Param("id") Integer id, @Param("cedula") String cedula);
    
    @Query("select c from PersonaEntity c "
    		+ "inner join PersonaVacunaEntity v on v.idPersona = c.idPersona "
    		+ "where c.vacunado = COALESCE(:vacunado, c.vacunado) and "
    		+ "v.tipoVacuna = COALESCE(:tipo, v.tipoVacuna) and "
    		+ "v.fechaVacuna between :fechaIni and :fechaEnd")
    Page<PersonaEntity> getPersonasFilter(Pageable pageable, @Param("vacunado") String vacunado, @Param("tipo") String tipo, @Param("fechaIni") Date fechaIni, @Param("fechaEnd") Date fechaEnd);
}
