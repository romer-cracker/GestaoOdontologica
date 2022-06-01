/**
 * 
 */
package br.com.gestaodentista.modelo.entidade;

import java.util.Objects;

/**
 * @author romer
 *
 */
public class Procedimento {

	private Long id;
	
	private String nome;
	
	private String descricao;



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Procedimento [id=").append(id).append(", nome=").append(nome).append(", descricao=")
				.append(descricao).append("]");
		return builder.toString();
	}




	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, nome);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Procedimento other = (Procedimento) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public boolean isNovo() {
		if(this.id == null) {
			return true;
		}else if(this.id != null && this.id > 0) {
			return false;
		}
		
		return id == null;
	}
}
