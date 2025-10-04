package br.com.lopessolutions.acesso.dto;

import java.io.Serializable;
import java.util.Objects;

public class BancoDTO implements Serializable {

	private static final long serialVersionUID = -2281147897038470497L;

	private Integer codigo;
	private String tipo;
	private String nomeDB;
	private String dataSource;
	private String usuarioDB;
	private String senhaDB;
	private String urlDB;
	private String prefeitura;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeDB() {
		return nomeDB;
	}

	public void setNomeDB(String nomeDB) {
		this.nomeDB = nomeDB;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getUsuarioDB() {
		return usuarioDB;
	}

	public void setUsuarioDB(String usuarioDB) {
		this.usuarioDB = usuarioDB;
	}

	public String getSenhaDB() {
		if (senhaDB == null || senhaDB.length() < 1) {
			return "alt@2024";
		}
		return senhaDB;
	}

	public void setSenhaDB(String senhaDB) {
		this.senhaDB = senhaDB;
	}

	public String getUrlDB() {
		return urlDB;
	}

	public void setUrlDB(String urlDB) {
		this.urlDB = urlDB;
	}

	public String getPrefeitura() {
		return prefeitura;
	}

	public void setPrefeitura(String prefeitura) {
		this.prefeitura = prefeitura;
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
		BancoDTO other = (BancoDTO) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
