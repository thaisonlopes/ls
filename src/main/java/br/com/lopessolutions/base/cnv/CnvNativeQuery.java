package br.com.lopessolutions.base.cnv;

import java.io.Serializable;
import java.util.List;

import br.com.lopessolutions.base.rne.CnvNativeQueryRNE;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Dependent
public class CnvNativeQuery extends CnvNativeQueryRNE {

	private static final long serialVersionUID = 5997115351893480564L;

	@Inject
	private CnvConexao cnvc;

	public EntityManager getSession() {
		return cnvc.getSession();
	}

	public void iniciarCnv(String sqlNrReg, String sql, int nrReg, Object... parametros) {
		try {

			super.iniciarCnv(getSession(), sqlNrReg, sql, nrReg, parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public void atualizarNR(Object... parametros) {
		try {

			super.atualizarNR(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public void proximo(Object... parametros) {
		try {

			super.proximo(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public void anterior(Object... parametros) {
		try {

			super.anterior(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public void primeiro(Object... parametros) {
		try {

			super.primeiro(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public void ultimo(Object... parametros) {
		try {

			super.ultimo(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public void atualizarPosisaoRegistro(Object... parametros) {

		try {

			EntityManager session = getSession();
			super.atualizarPosisaoRegistro(session, parametros);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public List<?> getLista(int posisaoInicial, int qtdeRegCarregado, Object... parametros) {
		try {

			return super.getLista(getSession(), posisaoInicial, qtdeRegCarregado, parametros);

		} catch (Exception e) {
			rollback(e);
		}

		return null;

	}

	public <T extends Serializable> T getEnt(Class<T> classToSearchPojo, Serializable key) {
		try {

			return super.getEnt(getSession(), classToSearchPojo, key);

		} catch (Exception e) {
			rollback(e);
		}

		return null;

	}

	public int getQuantidadeRegistroDM(Object... parametros) {

		try {

			EntityManager session = getSession();

			return super.getQuantidadeRegistroDM(session, parametros);

		} catch (Exception e) {
			rollback(e);
		}

		return 0;

	}

}
