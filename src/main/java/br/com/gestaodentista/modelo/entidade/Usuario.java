/**
 * 
 */
package br.com.gestaodentista.modelo.entidade;

import java.util.Objects;

/**
 * @author romer
 *
 */
public class Usuario {

	private Long id;

	private String login;

	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public int hashCode() {
		return Objects.hash(id, login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=").append(id).append(", login=").append(login).append(", senha=").append(senha)
				.append("]");
		return builder.toString();
	}

	public Usuario(Long id, String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	
}
