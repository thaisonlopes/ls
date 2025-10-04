package br.com.lopessolutions.entidades.superClasse;

import java.io.Serializable;

import br.com.lopessolutions.base.util.UsuarioDataHora;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@MappedSuperclass
public class EntityGeneric implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "userCreate", length = 250)
	private String userCreate;

	@Column(name = "userUpdate", length = 250)
	private String userUpdate;

	@Column(name = "dthrCreate")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dthrCreate;

	@Column(name = "dthrUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dthrUpdate;

	@Version
	@Column(name = "versao", nullable = false)
	private Integer versao;

	@PrePersist
	protected void prePersistDadosUsuarioDataLanc() {
		try {

			UsuarioDataHora uh = new UsuarioDataHora() {

				@Override
				public void setUsuarioAcesso(String nome) {
					userCreate = nome;
					userUpdate = nome;
				}

				@Override
				public void setDataHora(java.util.Date dataHora) {
					dthrCreate = dataHora;
					dthrUpdate = dataHora;
				}

			};
			uh.iniciarDadosUsuarioDataLanc();

		} catch (Exception e) {
		}
	}

	@PreUpdate
	protected void preUpdateDadosUsuarioDataLanc() {
		try {

			UsuarioDataHora uh = new UsuarioDataHora() {

				@Override
				public void setUsuarioAcesso(String nome) {
					userUpdate = nome;
				}

				@Override
				public void setDataHora(java.util.Date dataHora) {
					dthrUpdate = dataHora;
				}

			};
			uh.iniciarDadosUsuarioDataLanc();

		} catch (Exception e) {
		}
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public java.util.Date getDthrCreate() {
		return dthrCreate;
	}

	public void setDthrCreate(java.util.Date dthrCreate) {
		this.dthrCreate = dthrCreate;
	}

	public java.util.Date getDthrUpdate() {
		return dthrUpdate;
	}

	public void setDthrUpdate(java.util.Date dthrUpdate) {
		this.dthrUpdate = dthrUpdate;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

}
