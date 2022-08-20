package ec.reto.wmpv.ws.service.dto;


import java.io.Serializable;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Data
public class AutorizacionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String usuario;
    private String rol;
    private String token;

}
