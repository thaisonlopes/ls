package br.com.lopessolutions.base.relatorio;

import jakarta.ejb.Stateless;

/**
 * 
 * @author Thaison
 *
 */
@Stateless
public class RelatSession {

	@jakarta.inject.Inject
	private jakarta.persistence.EntityManager session;

	public jakarta.persistence.EntityManager getSession() {
		//this.session.joinTransaction();
		return this.session;
	}

}
