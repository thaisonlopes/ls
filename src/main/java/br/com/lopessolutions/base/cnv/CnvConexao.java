package br.com.lopessolutions.base.cnv;

import java.io.Serializable;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Stateless
public class CnvConexao implements Serializable {

	private static final long serialVersionUID = -9024041030111047618L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		return this.session;
	}

}
