package ec.reto.wmpv.ws.noc.service.utils;

import java.util.Objects;
import java.util.function.Function;

import ec.reto.wmpv.ws.service.dto.PersonaDto;
import ec.reto.wmpv.ws.service.dto.PersonaVacunaDto;
import ec.reto.wmpv.ws.service.dto.RolDto;
import ec.wmpv.reto.persistence.entity.PersonaEntity;
import ec.wmpv.reto.persistence.entity.PersonaVacunaEntity;
import ec.wmpv.reto.persistence.entity.RolEntity;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
public class EntityDtoConverter {

	public static Function<PersonaEntity, PersonaDto> personDto = entity -> {
    	var dto = new PersonaDto();
    	dto.setId(entity.getIdPersona());
    	dto.setCedula(entity.getCedula());
    	dto.setNombre(entity.getNombres());
    	dto.setApellido(entity.getApellidos());
    	dto.setEmail(entity.getEmail());
    	dto.setDireccion(entity.getDireccion());
    	dto.setTelefono(entity.getTelefono());
    	dto.setNacimiento(entity.getFechaNacimiento());
    	dto.setUsuario(entity.getUsuario());
    	dto.setClave(entity.getClave());
    	dto.setVacunado(entity.getVacunado());
    	if(!Objects.isNull(entity.getIdRol())) {
    		dto.setRol(new RolDto());
    		dto.getRol().setId(entity.getIdRol().getIdRol());
    		dto.getRol().setDescripcion(entity.getIdRol().getDescripcion());
    	}
        return dto;
    };
    
    public static Function<PersonaDto, PersonaEntity> personaEntity = dto -> {
    	var entity = new PersonaEntity();
    	entity.setIdPersona(dto.getId());
    	entity.setCedula(dto.getCedula());
    	entity.setNombres(dto.getNombre());
    	entity.setApellidos(dto.getApellido());
    	entity.setEmail(dto.getEmail());
    	entity.setDireccion(dto.getDireccion());
    	entity.setTelefono(dto.getTelefono());
    	entity.setFechaNacimiento(dto.getNacimiento());
    	entity.setUsuario(dto.getUsuario());
    	entity.setClave(dto.getClave());
    	entity.setVacunado(dto.getVacunado());
    	if(!Objects.isNull(dto.getRol())) {
    		entity.setIdRol(new RolEntity());
    		entity.getIdRol().setIdRol(dto.getRol().getId());
    		entity.getIdRol().setDescripcion(dto.getRol().getDescripcion());
    	}
        return entity;
    };
    
    public static Function<PersonaVacunaEntity, PersonaVacunaDto> vacunaDto = entity -> {
    	var dto = new PersonaVacunaDto();
    	dto.setId(entity.getIdVacuna());
    	dto.setTipo(entity.getTipoVacuna());
    	dto.setFecha(entity.getFechaVacuna());
    	dto.setDosis(entity.getNumeroDosis());
    	dto.setPersona(entity.getIdPersona());
        return dto;
    };
    
    public static Function<PersonaVacunaDto, PersonaVacunaEntity> vacunaEntity = dto -> {
    	var entity = new PersonaVacunaEntity();
    	entity.setIdVacuna(dto.getId());
    	entity.setTipoVacuna(dto.getTipo());
    	entity.setFechaVacuna(dto.getFecha());
    	entity.setNumeroDosis(dto.getDosis());
    	entity.setIdPersona(dto.getPersona());
        return entity;
    };
}
