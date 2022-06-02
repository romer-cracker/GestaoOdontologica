/**
 * 
 */
package br.com.gestaodentista.modelo.entidade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author romer
 *
 */
public class Contrato {

	private int id;
	
	private Long contratante;
	
	private Long contratada;
	
	private Date dataCriacao;
	
	private Date dataExpiracao;
	
	private Long pagamentos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getContratante() {
		return contratante;
	}

	public void setContratante(Long contratante) {
		this.contratante = contratante;
	}

	public Long getContratada() {
		return contratada;
	}

	public void setContratada(Long contratada) {
		this.contratada = contratada;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Long getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Long pagamentos) {
		this.pagamentos = pagamentos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contratada, contratante, dataCriacao, dataExpiracao, id, pagamentos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		return Objects.equals(contratada, other.contratada) && Objects.equals(contratante, other.contratante)
				&& Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(dataExpiracao, other.dataExpiracao)
				&& id == other.id && Objects.equals(pagamentos, other.pagamentos);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contrato [id=").append(id).append(", contratante=").append(contratante).append(", contratada=")
				.append(contratada).append(", dataCriacao=").append(dataCriacao).append(", dataExpiracao=")
				.append(dataExpiracao).append(", pagamentos=").append(pagamentos).append("]");
		return builder.toString();
	}
	
	public Contrato() {
		
	}

	
}
