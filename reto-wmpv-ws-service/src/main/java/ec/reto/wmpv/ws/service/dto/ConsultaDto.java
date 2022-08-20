package ec.reto.wmpv.ws.service.dto;


import java.io.Serializable;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Data
public class ConsultaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer size;
    private Integer idPersona;
    private String cedula;

}
