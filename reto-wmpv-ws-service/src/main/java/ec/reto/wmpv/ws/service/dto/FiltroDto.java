package ec.reto.wmpv.ws.service.dto;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Data
public class FiltroDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer size;
    private String vacunado;
    private String tipo;
    private Date fechaIni;
    private Date fechaFin;

}
