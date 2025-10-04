package br.com.lopessolutions.entidades.principal;

import java.io.Serializable;

import br.com.lopessolutions.base.criptografia.Criptografia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "intUsuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codUsuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(name = "nome", nullable = false, length = 250)
	private String nome;

	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Column(name = "senha", nullable = false, length = 500)
	private String senha;

	@Column(name = "foto", nullable = true)
	private byte[] foto;

	@Column(name = "email", length = 250)
	private String email;

	@Column(name = "telefone", length = 20)
	private String telefone;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoAcesso", nullable = false)
	private TipoAcesso tipoAcesso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoPermissao")
	private TipoPermissao tipoPermissao;

	@Version
	@Column(name = "versao", nullable = false)
	private Integer versao;

	@Column(name = "habilitado")
	private Boolean habilitado;

	@Column(name = "fornecedorMateria")
	private Boolean fornecedorMateria;

	@Column(name = "publicador")
	private Boolean publicador;

	public Usuario() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return Criptografia.getDescriptografar(cpf.trim());
	}

	public void setCpf(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
		this.cpf = Criptografia.getCriptografar(cpf.trim());
	}

	public String getCpfFormatado() {

		String cpfF = Criptografia.getDescriptografar(cpf);
		return cpfF.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

	}

	public String getSenha() {
		return Criptografia.getDescriptografar(senha);
	}

	public void setSenha(String senha) {
		this.senha = Criptografia.getCriptografar(senha.trim());
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoAcesso getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPermissao getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(TipoPermissao tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}

	public boolean isAutenticacaoValida(String password) {
		return (getSenha().compareTo(password) == 0);
	}

	public Boolean getFornecedorMateria() {
		return fornecedorMateria;
	}

	public void setFornecedorMateria(Boolean fornecedorMateria) {
		this.fornecedorMateria = fornecedorMateria;
	}

	public Boolean getPublicador() {
		return publicador;
	}

	public void setPublicador(Boolean publicador) {
		this.publicador = publicador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + "]";
	}

}
