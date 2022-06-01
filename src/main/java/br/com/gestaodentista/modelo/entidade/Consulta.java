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
public class Consulta {
	
	private Long id;
	
	private Long dentista;
	
	private Long paciente;
	
	private Date dataCriacao;
	
	private double valor;
	
	private Long tratamento;
	
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

	public Long getDentista() {
		return dentista;
	}

	public void setDentista(Long dentista) {
		this.dentista = dentista;
	}

	public Long getPaciente() {
		return paciente;
	}

	public void setPaciente(Long paciente) {
		this.paciente = paciente;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Long getTratamento() {
		return tratamento;
	}

	public void setTratamento(Long tratamento) {
		this.tratamento = tratamento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consulta [id=").append(id).append(", dentista=").append(dentista).append(", paciente=")
				.append(paciente).append(", dataCriacao=").append(dataCriacao).append(", valor=").append(valor)
				.append(", tratamento=").append(tratamento).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataCriacao, dentista, id, paciente, tratamento, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(dentista, other.dentista)
				&& Objects.equals(id, other.id) && Objects.equals(paciente, other.paciente)
				&& Objects.equals(tratamento, other.tratamento)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}
	

	public Consulta() {
		// TODO Auto-generated constructor stub
	}
	
		
	

}
