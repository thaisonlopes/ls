package br.com.lopessolutions.entidades.principal;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "intTipoPermissao")
public class TipoPermissao implements Serializable {

	private static final long serialVersionUID = 1L;

	// 01 - Sem Acesso
	// 02 - Acesso Personalizado
	// 20 - Acesso Total

	@Id
	@Column(name = "codTipoPermissao")
	private Integer codigo;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	public TipoPermissao() {

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
		TipoPermissao other = (TipoPermissao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return codigo + " - " + descricao;
	}

}
