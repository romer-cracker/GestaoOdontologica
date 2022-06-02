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
public class Pagamento {

	private Long id;

	private Date dataPagamento;
	private Date dataVencimento;
	private double valor;
	private double juros;
	private double valorTotal;

	private String descricao;
	
	public boolean isNovo() {
		if(this.id == null) {
			return true;
		}else if(this.id != null && this.id > 0) {
			return false;
		}
		
		return id == null;
	}


	public Pagamento() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pagamento [id=").append(id).append(", dataPagamento=").append(dataPagamento)
				.append(", dataVencimento=").append(dataVencimento).append(", valor=").append(valor).append(", juros=")
				.append(juros).append(", valorTotal=").append(valorTotal).append(", descricao=").append(descricao)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataPagamento, dataVencimento, descricao, id, juros, valor, valorTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(dataPagamento, other.dataPagamento)
				&& Objects.equals(dataVencimento, other.dataVencimento) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id)
				&& Double.doubleToLongBits(juros) == Double.doubleToLongBits(other.juros)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor)
				&& Double.doubleToLongBits(valorTotal) == Double.doubleToLongBits(other.valorTotal);
	}

	
}
