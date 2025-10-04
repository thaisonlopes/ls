package br.com.lopessolutions.entidades.principal;

import java.io.Serializable;
import java.util.Objects;

import br.com.lopessolutions.base.criptografia.Criptografia;
import jakarta.persistence.*;

@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "entidade", nullable = false)
	private String entidade;

	@Column(name = "cnpj", nullable = false)
	private String cnpj;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "telefoneFixo")
	private String telefoneFixo;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "brasao", columnDefinition = "bytea")
	private byte[] brasao;

	@Column(name = "email", length = 250)
	private String email;

	@Column(name = "senhaEmail", length = 500)
	private String senhaEmail;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public byte[] getBrasao() {
		return brasao;
	}

	public void setBrasao(byte[] brasao) {
		this.brasao = brasao;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getSenhaEmail() {
		return Criptografia.getDescriptografar(senhaEmail);
	}

	public void setSenhaEmail(String senhaEmail) {
		this.senhaEmail = Criptografia.getCriptografar(senhaEmail.trim());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brasao, cnpj, codigo, entidade, observacao, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		return Objects.equals(brasao, other.brasao) && Objects.equals(cnpj, other.cnpj)
				&& Objects.equals(codigo, other.codigo) && Objects.equals(entidade, other.entidade)
				&& Objects.equals(observacao, other.observacao)
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "Parametro [codigo=" + codigo + ", entidade=" + entidade + ", cnpj=" + cnpj 
				+ ", telefone=" + telefone + ", observacao=" + observacao + ", brasao=" + brasao + "]";
	}

}
