/**
 * 
 */
package br.com.gestaodentista.modelo.entidade;

import java.sql.Date;
import java.util.Objects;

/**
 * @author romer
 *
 */
public class Auditor {

	private Long id;
	private Date dataCriacao;
	private Date dataUltimaModificacao;
	private String modificadoPor;
	private String criadoPor;
	
	public boolean isNovo() {
		if(this.id == null) {
			return true;
		}else if(this.id != null && this.id > 0) {
			return false;
		}
		
		return id == null;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}
	public void setDataUltimaModificacao(Date dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	
	
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public String getCriadoPor() {
		return criadoPor;
	}
	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(criadoPor, dataCriacao, dataUltimaModificacao, id, modificadoPor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditor other = (Auditor) obj;
		return Objects.equals(criadoPor, other.criadoPor) && Objects.equals(dataCriacao, other.dataCriacao)
				&& Objects.equals(dataUltimaModificacao, other.dataUltimaModificacao) && Objects.equals(id, other.id)
				&& Objects.equals(modificadoPor, other.modificadoPor);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Auditor [id=").append(id).append(", dataCriacao=").append(dataCriacao)
				.append(", dataUltimaModificacao=").append(dataUltimaModificacao).append(", modificadoPor=")
				.append(modificadoPor).append(", criadoPor=").append(criadoPor).append("]");
		return builder.toString();
	}

	public Auditor() {
		
	}
}
