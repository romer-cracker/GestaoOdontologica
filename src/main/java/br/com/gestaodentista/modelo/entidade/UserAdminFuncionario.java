package br.com.gestaodentista.modelo.entidade;

import java.util.Objects;

public class UserAdminFuncionario {

	private Long id;
	
	private String nome;
	
	private String admissao;
	
	private String login;
	
	private String senha;
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAdmissao() {
		return admissao;
	}

	public void setAdmissao(String admissao) {
		this.admissao = admissao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAdminFuncionario [id=").append(id).append(", nome=").append(nome).append(", admissao=")
				.append(admissao).append(", login=").append(login).append(", senha=").append(senha).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(admissao, id, login, nome, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAdminFuncionario other = (UserAdminFuncionario) obj;
		return Objects.equals(admissao, other.admissao) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}
	
	
}
