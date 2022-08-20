package ec.wmpv.reto.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "persona", schema = "public", catalog = "configuration")
public class PersonaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Basic
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Basic
    @Column(name = "usuario", nullable = true, length = 20)
    private String usuario;

    @Basic
    @Column(name = "clave", nullable = true, length = 100)
    private String clave;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", nullable = true)
    private Date fechaNacimiento;

    @Basic
    @Column(name = "direccion", nullable = true, length = 80)
    private String direccion;

    @Basic
    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;
    
    @Basic
    @Column(name = "vacunado", nullable = true, length = 1)
    private String vacunado;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolEntity idRol;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaEntity that = (PersonaEntity) o;

        if (idPersona != that.idPersona) return false;
        if (!Objects.equals(cedula, that.cedula))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idPersona != null ? idPersona.hashCode() : 0;
        result = 31 * result + (cedula != null ? cedula.hashCode() : 0);
        return result;
    }
}
