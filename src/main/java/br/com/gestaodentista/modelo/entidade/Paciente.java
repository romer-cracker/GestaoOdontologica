/**
 * 
 */
package br.com.gestaodentista.modelo.entidade;

import java.util.Objects;

/**
 * @author romer
 *
 */
public class Paciente {

	private Long id;
	
	private String nome;
	
	private String rg;
	
	private String cpf;
	
	private String sexo;
	
	private String endereco;
	
	private String bairro;
	
	private String municipio;
	
	private String cep;
	
	private String uf;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	private String grupo;
	
	private String situacao;
	
	
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paciente [id=").append(id).append(", nome=").append(nome).append(", rg=").append(rg)
				.append(", cpf=").append(cpf).append(", sexo=").append(sexo).append(", endereco=")
				.append(endereco).append(", bairro=").append(bairro).append(", municipio=").append(municipio)
				.append(", cep=").append(cep).append(", uf=").append(uf).append(", telefone=").append(telefone)
				.append(", celular=").append(celular).append(", email=").append(email).append(", grupo=").append(grupo)
				.append(", situacao=").append(situacao).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, celular, cep, cpf, email, endereco, grupo, id, municipio, nome, rg, sexo,
				situacao, telefone, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(celular, other.celular)
				&& Objects.equals(cep, other.cep) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(email, other.email) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(grupo, other.grupo) && Objects.equals(id, other.id)
				&& Objects.equals(municipio, other.municipio) && Objects.equals(nome, other.nome)
				&& Objects.equals(rg, other.rg) && Objects.equals(sexo, other.sexo)
				&& Objects.equals(situacao, other.situacao) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(uf, other.uf);
	}

	public Paciente(Long id, String nome, String rg, String cpf, String sexo, String endereco, String bairro,
			String municipio, String cep, String uf, String telefone, String celular, String email, String grupo,
			String situacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		this.bairro = bairro;
		this.municipio = municipio;
		this.cep = cep;
		this.uf = uf;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.grupo = grupo;
		this.situacao = situacao;
	}
	
	public Paciente(String nome, String rg, String cpf, String sexo, String endereco, String bairro,
			String municipio, String cep, String uf, String telefone, String celular, String email, String grupo,
			String situacao) {
		super();
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		this.bairro = bairro;
		this.municipio = municipio;
		this.cep = cep;
		this.uf = uf;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.grupo = grupo;
		this.situacao = situacao;
	}

	public Paciente() {
		
	}	
	
	
	
	
	
	
	
	
	
}
