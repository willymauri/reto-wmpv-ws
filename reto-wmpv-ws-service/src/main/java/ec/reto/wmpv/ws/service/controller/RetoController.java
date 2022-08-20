package ec.reto.wmpv.ws.service.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.reto.wmpv.ws.noc.service.auth.AuthorizationFilter;
import ec.reto.wmpv.ws.noc.service.exception.WsServiceException;
import ec.reto.wmpv.ws.noc.service.utils.EntityDtoConverter;
import ec.reto.wmpv.ws.service.dto.AutorizacionDto;
import ec.reto.wmpv.ws.service.dto.ConsultaDto;
import ec.reto.wmpv.ws.service.dto.FiltroDto;
import ec.reto.wmpv.ws.service.dto.LoginDto;
import ec.reto.wmpv.ws.service.dto.PersonaDto;
import ec.reto.wmpv.ws.service.dto.PersonaVacunaDto;
import ec.reto.wmpv.ws.service.dto.VacunadoDto;
import ec.reto.wmpv.ws.service.model.EnumResponse;
import ec.reto.wmpv.ws.service.model.ResponseData;
import ec.reto.wmpv.ws.service.model.ResponseModel;
import ec.wmpv.reto.persistence.entity.PersonaEntity;
import ec.wmpv.reto.persistence.service.PersonaEntityService;
import ec.wmpv.reto.persistence.service.PersonaVacunaEntityService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @autor william.patino [Date: 06 apr. 2022]
 **/
@RestController
@RequestMapping("/rest/wmpv")
@Slf4j
public class RetoController {
    
	@Autowired
    Environment env;
	
	@Autowired
    PersonaEntityService personaService;
	
	@Autowired
    PersonaVacunaEntityService vacunaService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<ResponseData<AutorizacionDto>> getLogin(@RequestBody LoginDto request) {
		ResponseData<AutorizacionDto> response = new ResponseData<>(EnumResponse.OK.code());
		try {
			PersonaEntity persona = personaService.getPersona(request.getUser());
			
			if(!request.getPass().equals(persona.getClave())) {
				throw new WsServiceException("Persona no autorizada.");
			}
			if(Objects.isNull(persona.getIdRol())) {
				throw new WsServiceException("Perfil no registrado.");
			}
			var autorizado = new AutorizacionDto();
			autorizado.setId(persona.getIdPersona());
			autorizado.setUsuario(persona.getUsuario());
			autorizado.setRol(persona.getIdRol().getDescripcion());
			
			String token = AuthorizationFilter.getJWTToken(persona.getUsuario(), persona.getIdRol().getDescripcion(), request.isKeepSession());
			autorizado.setToken(token);
			response.setData(autorizado);
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    @PostMapping(value = "/savePersona")
    public ResponseEntity<ResponseModel> savePersona(@RequestBody PersonaDto request) {
    	var response = new ResponseModel(EnumResponse.OK.code());
        try {
        	personaService.save(EntityDtoConverter.personaEntity.apply(request));
            response.setMessage("Registro exitoso.");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = "/saveVacuna")
    public ResponseEntity<ResponseModel> saveVacuna(@RequestBody PersonaVacunaDto request) {
    	var response = new ResponseModel(EnumResponse.OK.code());
        try {
        	vacunaService.save(EntityDtoConverter.vacunaEntity.apply(request));
            response.setMessage("Registro exitoso.");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //response.setData(!Objects.isNull(nodo) ? EntityDtoConverter.nodoNocDto.apply(nodo): null);
    
    @PostMapping(value = "/getPersonas")
    public ResponseEntity<ResponseData<List<PersonaDto>>> getPersonas(@RequestBody ConsultaDto request) {
    	ResponseData<List<PersonaDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		 Page<PersonaEntity> personas = personaService.getPersonas(PageRequest.of(request.getPage(), request.getSize(), Sort.by("idPersona").descending()), request.getIdPersona(), request.getCedula());
    		 response.setData(personas.getContent().stream().
                     map(r -> EntityDtoConverter.personDto.apply(r)).collect(Collectors.toList()));
    		 response.setMessage("Consulta exitosa.");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = "/getVacunas")
    public ResponseEntity<ResponseData<List<PersonaVacunaDto>>> getVacunas(@RequestBody VacunadoDto request) {
    	ResponseData<List<PersonaVacunaDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		 response.setData(vacunaService.getVacunasByPersona(request.getIdPersona()).stream().
                     map(r -> EntityDtoConverter.vacunaDto.apply(r)).collect(Collectors.toList()));
    		 response.setMessage("Consulta exitosa.");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = "/getPersonasFiltro")
    public ResponseEntity<ResponseData<List<PersonaDto>>> getPersonasFiltro(@RequestBody FiltroDto request) {
    	ResponseData<List<PersonaDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		 Page<PersonaEntity> personas = personaService.getPersonasFilter(PageRequest.of(request.getPage(), request.getSize(), Sort.by("idPersona").descending()), request.getVacunado(), request.getTipo(), request.getFechaIni(), request.getFechaFin());
    		 response.setData(personas.getContent().stream().
                     map(r -> EntityDtoConverter.personDto.apply(r)).collect(Collectors.toList()));
    		 response.setMessage("Consulta exitosa.");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
