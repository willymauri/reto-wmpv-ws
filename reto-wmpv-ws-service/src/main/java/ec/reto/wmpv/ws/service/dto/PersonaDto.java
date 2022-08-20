package ec.reto.wmpv.ws.service.dto;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
@Data
public class PersonaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    private String usuario;
    private String clave;
    private String vacunado;
    private Date nacimiento;
    private RolDto rol;
}
