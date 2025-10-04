package br.com.lopessolutions.base.autoComplete;

import java.io.Serializable;
import java.util.List;
import br.com.lopessolutions.base.rne.GenericRNE;
import br.com.lopessolutions.entidades.principal.Log;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Stateless
public class AutoCompleteDAO extends GenericRNE {

	private static final long serialVersionUID = 3730612187462103714L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		return this.session;
	}

	public AutoCompleteDAO() {
		super();
	}

	@Override
	protected void setLog(Log log, Serializable pojo) {

	}

	public <T extends Serializable> List<T> getList(Class<T> classToSearch, String query, int maxResults, Object... parametros) {

		try {

			EntityManager session = getSession();

			return super.getList(session, classToSearch, query, maxResults, parametros);

		} catch (Exception e) {
			e.printStackTrace();
			rollback(e);
		}

		return null;

	}
	
	public List<?> getListNativeQuery(String sql, Object... parametros) {
		return super.getListNativeQuery(session, sql, parametros);
	}

	public List<?> getList(String query, int maxResults, Object... parametros) {

		try {

			EntityManager session = getSession();

			return super.getListNativeQuery(session, maxResults, query, parametros);

		} catch (Exception e) {
			e.printStackTrace();
			rollback(e);
		}

		return null;

	}

}
