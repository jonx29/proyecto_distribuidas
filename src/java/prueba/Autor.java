/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JONATHAN
 */
@Entity
@Table(name = "AUTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a")
    , @NamedQuery(name = "Autor.findByAuCodigo", query = "SELECT a FROM Autor a WHERE a.auCodigo = :auCodigo")
    , @NamedQuery(name = "Autor.findByAuNombre", query = "SELECT a FROM Autor a WHERE a.auNombre = :auNombre")
    , @NamedQuery(name = "Autor.findByAuApellido", query = "SELECT a FROM Autor a WHERE a.auApellido = :auApellido")})
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "AU_CODIGO")
    private String auCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AU_NOMBRE")
    private String auNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AU_APELLIDO")
    private String auApellido;

    public Autor() {
    }

    public Autor(String auCodigo) {
        this.auCodigo = auCodigo;
    }

    public Autor(String auCodigo, String auNombre, String auApellido) {
        this.auCodigo = auCodigo;
        this.auNombre = auNombre;
        this.auApellido = auApellido;
    }

    public String getAuCodigo() {
        return auCodigo;
    }

    public void setAuCodigo(String auCodigo) {
        this.auCodigo = auCodigo;
    }

    public String getAuNombre() {
        return auNombre;
    }

    public void setAuNombre(String auNombre) {
        this.auNombre = auNombre;
    }

    public String getAuApellido() {
        return auApellido;
    }

    public void setAuApellido(String auApellido) {
        this.auApellido = auApellido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auCodigo != null ? auCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.auCodigo == null && other.auCodigo != null) || (this.auCodigo != null && !this.auCodigo.equals(other.auCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prueba.Autor[ auCodigo=" + auCodigo + " ]";
    }
    
}
