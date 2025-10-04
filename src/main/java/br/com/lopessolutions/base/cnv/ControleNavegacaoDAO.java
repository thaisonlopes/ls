package br.com.lopessolutions.base.cnv;

import java.io.Serializable;
import java.util.List;


import br.com.lopessolutions.base.rne.ControleNavegacaoRNE;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Dependent
public class ControleNavegacaoDAO extends ControleNavegacaoRNE {

	private static final long serialVersionUID = -127377161324870502L;

	@Inject
	private CnvConexao cnvc;

	public EntityManager getSession() {
		return cnvc.getSession();
	}

	public ControleNavegacaoDAO() {

	}

	public void iniciarControleNavegacao(String nomeEntidade, String condicao, String ordenacao, int qtdeRegPorPag, Object... parametros) {
		try {

			super.iniciarControleNavegacao(getSession(), nomeEntidade, condicao, ordenacao, qtdeRegPorPag, parametros);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public void iniciarControleNavegacao(String sqlQuantidadeRegistro, String sqlBuscaLista, int qtdeRegPorPag, Object... parametros) {
		try {

			super.iniciarControleNavegacao(getSession(), sqlQuantidadeRegistro, sqlBuscaLista, qtdeRegPorPag, parametros);

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

			super.atualizarPosisaoRegistro(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}
	}

	public <T extends Serializable> List<T> getLista(int posisaoInicial, int qtdeRegCarregado, Object... parametros) {
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

			return super.getQuantidadeRegistroDM(getSession(), parametros);

		} catch (Exception e) {
			rollback(e);
		}

		return 0;

	}

	// Retorna lista de Objetos II
	public List<?> getPureList(Integer first, Integer pageSize, String query, Object... keys) {
		try {

			return super.getPureList(getSession(), first, pageSize, String.class, query, keys);

		} catch (Exception e) {
			rollback(e);
		}

		return null;

	}

}
