package br.com.lopessolutions.entidades.principal;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Entity
@Table(name = "intPermissao", uniqueConstraints = @UniqueConstraint(columnNames = { "codUsuario",
		"codFormulario" }))
public class Permissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codPermissao")
	private Integer codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codUsuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codFormulario", nullable = false)
	private Formulario formulario;

	@Column(name = "data")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date data;

	@Column(name = "acesso", nullable = false)
	private boolean acesso = false;

	@Column(name = "salvar", nullable = false)
	private boolean salvar = false;

	@Column(name = "excluir", nullable = false)
	private boolean excluir = false;

	@Column(name = "imprimir", nullable = false)
	private boolean imprimir = false;

	@Version
	@Column(name = "versao", nullable = false)
	private Integer versao;

	public Permissao() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public boolean isAcesso() {
		return acesso;
	}

	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}

	public boolean isSalvar() {
		return salvar;
	}

	public void setSalvar(boolean salvar) {
		this.salvar = salvar;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public boolean isImprimir() {
		return imprimir;
	}

	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	 

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public java.util.Date getData() {
		return data;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissao other = (Permissao) obj;
		return Objects.equals(codigo, other.codigo);
	}
 

}
