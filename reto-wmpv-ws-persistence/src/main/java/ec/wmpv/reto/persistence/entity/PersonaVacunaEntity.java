package ec.wmpv.reto.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "persona_vacuna", schema = "public", catalog = "configuration")
public class PersonaVacunaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacuna", nullable = false)
	private Integer idVacuna;

	@Basic
    @Column(name = "tipo_vacuna", nullable = false, length = 15)
	private String tipoVacuna;
	
	@Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_vacuna", nullable = false)
    private Date fechaVacuna;
    
	@Basic
    @Column(name = "numero_dosis", nullable = false)
	private Integer numeroDosis;

	@Basic
    @Column(name = "id_persona", nullable = false)
	private Integer idPersona;
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaVacunaEntity that = (PersonaVacunaEntity) o;

        if (idVacuna != that.idVacuna) return false;
        if (tipoVacuna != null ? !tipoVacuna.equals(that.tipoVacuna) : that.tipoVacuna != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idVacuna != null ? idVacuna.hashCode() : 0;
        result = 31 * result + (tipoVacuna != null ? tipoVacuna.hashCode() : 0);
        return result;
    }
}
