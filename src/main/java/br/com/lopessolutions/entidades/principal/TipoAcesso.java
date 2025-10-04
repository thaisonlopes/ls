package br.com.lopessolutions.entidades.principal;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "intTipoAcesso")
public class TipoAcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	// 01 - Sem Acesso
	// 10 - Acesso a Unidade Gestora
	// 20 - Acesso Total

	@Id
	@Column(name = "codTipoAcesso")
	private Integer codigo;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@Version
	@Column(name = "versao", nullable = false)
	private Integer versao;

	public TipoAcesso() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
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
		TipoAcesso other = (TipoAcesso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
