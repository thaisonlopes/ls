package br.com.lopessolutions.base.relatorio;

public class TabelaRelatorios implements java.io.Serializable {

	private static final long serialVersionUID = 7482062214460737606L;

	private int id;
	private String descricao;
	private Relatorio relatorio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		TabelaRelatorios other = (TabelaRelatorios) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
