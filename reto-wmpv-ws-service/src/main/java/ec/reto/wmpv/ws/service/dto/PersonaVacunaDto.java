package ec.reto.wmpv.ws.service.dto;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Data
public class PersonaVacunaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String tipo;
    private Date fecha;
    private Integer dosis;
    private Integer persona;
}
